package com.victorbesseler.bookshelf.book;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String author;

    private Integer rating;

    private String coverImage;

    protected Book() {
    }

    public Book(String title, String author, Integer rating, String coverImage) {
        this.title = title;
        this.author = author;
        this.rating = rating;
        this.coverImage = coverImage;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public Integer getRating() {
        return rating;
    }

    public String getCoverImage() {
        return coverImage;
    }
}