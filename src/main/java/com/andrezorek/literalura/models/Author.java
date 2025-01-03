package com.andrezorek.literalura.models;

import com.andrezorek.literalura.dto.AuthorDTO;

// Classe autor
public class Author {
    private String name;
    private Integer birthYear;
    private Integer deathYear;

    public Author(AuthorDTO authorData){
        this.name = authorData.name();
        this.birthYear = authorData.birthYear();
        this.deathYear = authorData.deathYear();
    }

    public String getName() {
        return name;
    }


    public Integer getBirthYear() {
        return birthYear;
    }


    public Integer getDeathYear() {
        return deathYear;
    }

}
