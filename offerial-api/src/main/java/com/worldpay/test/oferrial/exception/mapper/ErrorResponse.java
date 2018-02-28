package com.worldpay.test.oferrial.exception.mapper;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.worldpay.test.oferrial.rest.Link;

import static com.worldpay.test.oferrial.rest.ApiLinksBuilder.newLinks;
import static com.worldpay.test.oferrial.rest.StandardLinks.STANDARD_LINKS;

final class ErrorResponse {

    @JsonProperty
    private final String message;
    @JsonProperty
    private final Map<String, Link> links;

    ErrorResponse(String message) {
        this.message = message;
        links = newLinks().add(STANDARD_LINKS).build().getLinks();
    }

    ErrorResponse(Exception exception) {
        this(exception.getMessage());
    }

    public String getMessage() {
        return message;
    }

    public Map<String, Link> getLinks() {
        return links;
    }

}
