package com.worldpay.test.oferrial.rest;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.worldpay.test.oferrial.domain.OfferId;

import java.util.Map;

public class OfferCreatedResponse {
    @JsonProperty
    private final String uniqueId;

    @JsonProperty
    private final Map<String, Link> links;

    @JsonCreator
    public OfferCreatedResponse(@JsonProperty("newOfferId") OfferId newOfferId,
                                @JsonProperty("links") Map<String, Link> links) {
        uniqueId = newOfferId.toIdString();
        this.links = links;
    }

    public String getUniqueId() {
        return uniqueId;
    }

    public Map<String, Link> getLinks() {
        return links;
    }
}
