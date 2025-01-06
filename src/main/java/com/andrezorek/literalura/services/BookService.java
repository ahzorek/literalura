package com.andrezorek.literalura.services;

import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import jakarta.persistence.EntityManager;

import com.andrezorek.literalura.dto.BookDTO;
import com.andrezorek.literalura.models.Author;
import com.andrezorek.literalura.models.Book;
import com.andrezorek.literalura.repository.BookRepository;

import java.util.List;

@Service
public class BookService {

    private final AuthorService authorService;
    private final BookRepository bookRepository;

    @PersistenceContext
    private final EntityManager entityManager;

    @Autowired
    public BookService(AuthorService authorService, BookRepository bookRepository, EntityManager entityManager) {
        this.authorService = authorService;
        this.bookRepository = bookRepository;
        this.entityManager = entityManager;
    }

    @Transactional
    public Book createBook(BookDTO bookData) {
        // Resolve o autor (busca ou cria) usando o AuthorService
        Author author = authorService.findOrCreateNew(bookData.authors().get(0)); // passa authordto para localizar o author por nome ou criar novo autor

        // Garante que o autor esteja no contexto de persistência (thanks gpt)
        author = entityManager.merge(author);

        // construtor de book recebe bookdto e ref ao autor
        Book book = new Book(bookData, author);

        // salva o livro e retorna ref ao obj salvo
        return bookRepository.save(book);
    }

    public List<Book> findAllBooks() {
        return bookRepository.findAll();
    }
}


