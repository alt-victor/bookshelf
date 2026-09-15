package com.victorbesseler.bookshelf.book;

import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(
        replace = AutoConfigureTestDatabase.Replace.NONE
)
class BookRepositoryTest {

    @Autowired
    private BookRepository repository;

    @Autowired
    private EntityManager entityManager;

    @Test
    void shouldSaveAndFindBookById() {
        // Prepara um livro
        Book book = new Book(
                "O Hobbit",
                "J. R. R. Tolkien",
                5,
                "hobbit.jpg"
        );

        // Grava no PostgreSQL e limpa os objetos mantidos pelo JPA
        Book saved = repository.saveAndFlush(book);
        Long id = saved.getId();

        assertNotNull(id);
        entityManager.clear();

        // Busca novamente no banco
        Book found = repository.findById(id).orElseThrow();

        // Confere os dados recuperados
        assertEquals(id, found.getId());
        assertEquals("O Hobbit", found.getTitle());
        assertEquals("J. R. R. Tolkien", found.getAuthor());
        assertEquals(Integer.valueOf(5), found.getRating());
        assertEquals("hobbit.jpg", found.getCoverImage());
    }
}