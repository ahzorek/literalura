package com.andrezorek.literalura.services;

import com.andrezorek.literalura.dto.APIResponseDTO;
import com.andrezorek.literalura.dto.BookDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Optional;

import static com.andrezorek.literalura.utils.RequestHandler.fetch;

public class APIService {

    public static Optional<BookDTO> searchBookByName(String query) {
        var res = fetch("https://gutendex.com/books/?search="+query);

        // desseriializa o JSON para o data transfer object
        ObjectMapper mapper = new ObjectMapper();

        APIResponseDTO response;
        try {
            response = mapper.readValue(res, APIResponseDTO.class);
            return Optional.ofNullable(response.results().get(0));

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }
}
