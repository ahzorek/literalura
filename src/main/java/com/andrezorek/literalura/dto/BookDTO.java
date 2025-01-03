package com.andrezorek.literalura.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record BookDTO(
        @JsonProperty("title") String title,
        @JsonProperty("authors") List<AuthorDTO> authors,
        @JsonProperty("languages") List<String> languages,
        @JsonProperty("download_count") int downloadCount
) {}
