package com.andrezorek.literalura.models;

public enum Language {
    PORTUGUESE("pt"),
    ENGLISH("en"),
    FRENCH("fr"),
    SPANISH("es"),
    POLISH("pl"),
    RUSSIAN("ru"),
    ITALIAN("it"),
    GERMAN("de"),
    FINNISH("fi"),
    DUTCH("nl");

    private String langCode;

    Language(String langCode){
        this.langCode = langCode;
    }

    public static Language fromString(String code){
        for (Language language : Language.values()){
            if(language.langCode.equalsIgnoreCase(code)){
                return language;
            }
        }
        throw new IllegalArgumentException("Idioma não reconhecido :::: " + code);
    }
}