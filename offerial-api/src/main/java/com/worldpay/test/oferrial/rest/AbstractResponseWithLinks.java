package com.worldpay.test.oferrial.rest;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

@JsonInclude(Include.NON_NULL)
public abstract class AbstractResponseWithLinks {

    @JsonProperty
    private final Map<String, Link> links;

    protected AbstractResponseWithLinks(ApiLinksBuilder links) {
        this.links = links.build().getLinks();
    }

    @JsonCreator
    protected AbstractResponseWithLinks(@JsonProperty("links") Map<String, Link> links) {
        this.links = links;
    }

    public Map<String, Link> getLinks() {
        return links;
    }

}
