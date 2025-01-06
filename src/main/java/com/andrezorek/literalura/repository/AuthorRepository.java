package com.andrezorek.literalura.repository;

import com.andrezorek.literalura.models.Author;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AuthorRepository extends JpaRepository<Author, Integer> {
    Optional<Author> findByNameEqualsIgnoreCase(String authorName);

    List<Author> findByBirthYearLessThanEqualAndDeathYearGreaterThanEqualOrDeathYearIsNull(int birthYear, int deathYear);

    List<Author> findAll();

}
