package com.andrezorek.literalura.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public record APIResponseDTO(
        @JsonProperty("count") int count,
        @JsonProperty("next") String next,
        @JsonProperty("previous") String previous,
        @JsonProperty("results") List<BookDTO> results
) {}
