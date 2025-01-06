package com.andrezorek.literalura.repository;

import com.andrezorek.literalura.models.Book;
import com.andrezorek.literalura.models.Language;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Integer> {
    // Buscar livros por idioma
    List<Book> findByLang(Language language);

    // Contar livros por idioma
    int countByLang(Language language);
}
