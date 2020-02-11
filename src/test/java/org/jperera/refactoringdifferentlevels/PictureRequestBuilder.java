package org.jperera.refactoringdifferentlevels;

import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class PictureRequestBuilder {

    private ObjectNode jsonNode;

    static PictureRequestBuilder aRequest() {
        return new PictureRequestBuilder();
    }

    private PictureRequestBuilder() {
        this.jsonNode = JsonNodeFactory.instance.objectNode();
    }

    PictureRequestBuilder with(String field, Integer value) {
        this.jsonNode.put(field, value);
        return this;
    }

    PictureRequestBuilder with(String field, String value) {
        this.jsonNode.put(field, value);
        return this;
    }

    String json() {
        return jsonNode.toString();
    }

}
