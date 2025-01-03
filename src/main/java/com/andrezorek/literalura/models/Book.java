package com.andrezorek.literalura.models;

import com.andrezorek.literalura.dto.BookDTO;
import jakarta.persistence.*;

// Classe para cada livro
@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    @Column(unique = true)
    private String title;

    @Transient
    private Author author;

    private int download_count;

    public Book(){}

    public Book(BookDTO bookData) {
        this.title = bookData.title();
        this.download_count = bookData.downloadCount();
        this.author = new Author(bookData.authors().get(0));
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
        return "Título: " + title + "\n" +
                "Autor: " +
                author.getName() + " (" +
                (author.getBirthYear() == null ? "Desconhecido" : author.getBirthYear()) +
                " - " +
                (author.getDeathYear() == null ? "Desconhecido" : author.getDeathYear()) +
                ")" + "\n" +
                "Downloads: " + download_count + "\n";
    }
}
