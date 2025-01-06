package com.andrezorek.literalura.models;

import com.andrezorek.literalura.dto.BookDTO;
import jakarta.persistence.*;

// Classe detalhada para cada livro
@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    private String title;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private Author author;

    private int download_count;

    @Enumerated(EnumType.STRING)
    private Language lang;

    public Book(){}

    public Book(BookDTO bookData, Author author) {
        this.title = bookData.title();
        this.download_count = bookData.downloadCount();
        this.lang = Language.fromString(bookData.languages().get(0));
        this.author = author;
    }

    public int getDownload_count() {
        return download_count;
    }

    public void setDownload_count(int download_count) {
        this.download_count = download_count;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // Método para exibir formatadamente
    @Override
    public String toString() {
        return "Title: " + title + "\n" +
                "Author: " +
                author.getName() + " (" +
                (author.getBirthYear() == null ? "Desconhecido" : author.getBirthYear()) +
                " - " +
                (author.getDeathYear() == null ? "Desconhecido" : author.getDeathYear()) +
                ")" + "\n" +
                "Language: " + lang + "\n" +
                "Downloads: " + download_count + "\n";
    }

    public void setLang(Language language) {
    }
}
