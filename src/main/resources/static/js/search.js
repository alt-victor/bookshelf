(() => {
    const input = document.querySelector('#book-search');
    if (!input) return;

    const cards = Array.from(document.querySelectorAll('.book-card'));
    const noResults = document.querySelector('#no-results');
    const status = document.querySelector('#search-status');
    const normalize = value => value.normalize('NFD').replace(/[\u0300-\u036f]/g, '').toLocaleLowerCase('pt-BR').trim();

    function filterBooks() {
        const query = normalize(input.value);
        let visible = 0;
        cards.forEach(card => {
            const content = normalize(`${card.dataset.title || ''} ${card.dataset.author || ''}`);
            const matches = content.includes(query);
            card.hidden = !matches;
            if (matches) visible++;
        });
        noResults.hidden = cards.length === 0 || visible > 0;
        status.textContent = query && cards.length > 0
            ? `${visible} ${visible === 1 ? 'livro encontrado' : 'livros encontrados'}`
            : '';
    }

    input.addEventListener('input', filterBooks);
    document.querySelector('#clear-search').addEventListener('click', () => {
        input.value = '';
        filterBooks();
        input.focus();
    });
})();
