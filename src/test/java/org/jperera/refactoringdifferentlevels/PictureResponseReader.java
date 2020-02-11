package org.jperera.refactoringdifferentlevels;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class PictureResponseReader {

    private JsonNode jsonRoot;

    static PictureResponseReader reader(String response) throws JsonProcessingException {
        return new PictureResponseReader(response);
    }

    PictureResponseReader(String response) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        this.jsonRoot = objectMapper.readTree(response);
    }

    Integer id() {
        return jsonRoot.get("id").asInt();
    }

}
