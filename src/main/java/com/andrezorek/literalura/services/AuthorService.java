package com.andrezorek.literalura.services;

import com.andrezorek.literalura.dto.AuthorDTO;
import com.andrezorek.literalura.models.Author;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import com.andrezorek.literalura.repository.AuthorRepository;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class AuthorService {

    private final AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Transactional
    public Author findOrCreateNew(AuthorDTO authorDTO) {
        // tenta encontrar autor peo nome ou cria novo autor
        return authorRepository.findByNameEqualsIgnoreCase(authorDTO.name())
                .orElseGet(() -> new Author(authorDTO)); // Cria o autor com o DTO, incluindo nome, data de nascimento e falecimento
    }

    public List<Author> getAuthorsAliveInYear(int year) {
        List<Author> authors = authorRepository.findByBirthYearLessThanEqualAndDeathYearGreaterThanEqualOrDeathYearIsNull(year, year);

        // exclui os autores com birthYear e deathYear como null
        return authors.stream()
                .filter(author -> author.getBirthYear() != null && author.getDeathYear() != null)
                .collect(Collectors.toList());
    }

    public List<Author> getAll(){
        return authorRepository.findAll();
    }
}
