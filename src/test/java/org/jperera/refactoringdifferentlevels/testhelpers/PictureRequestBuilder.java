package org.jperera.refactoringdifferentlevels.testhelpers;

import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class PictureRequestBuilder {

    private ObjectNode jsonNode;

    public static PictureRequestBuilder aRequest() {
        return new PictureRequestBuilder();
    }

    private PictureRequestBuilder() {
        this.jsonNode = JsonNodeFactory.instance.objectNode();
    }

    public PictureRequestBuilder with(String field, Integer value) {
        this.jsonNode.put(field, value);
        return this;
    }

    public PictureRequestBuilder with(String field, String value) {
        this.jsonNode.put(field, value);
        return this;
    }

    public String json() {
        return jsonNode.toString();
    }

}
