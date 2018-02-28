package com.worldpay.test.oferrial.rest;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

final public class ApiLinks {

    @JsonProperty
    private final Map<String, Link> links;

    ApiLinks(Map<String, Link> links) {
        this.links = links;
    }

    public Map<String, Link> getLinks() {
        return links;
    }

}
