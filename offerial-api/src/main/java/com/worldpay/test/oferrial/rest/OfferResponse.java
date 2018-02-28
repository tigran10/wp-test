package com.worldpay.test.oferrial.rest;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.worldpay.test.oferrial.domain.OfferDetails;

@JsonInclude(Include.NON_NULL)
public class OfferResponse extends AbstractResponseWithLinks {

    @JsonProperty
    private final OfferDetails offerDetails;

    public OfferResponse(OfferDetails offerDetails, ApiLinksBuilder links) {
        super(links);
        this.offerDetails = offerDetails;
    }

    @JsonCreator
    public OfferResponse(@JsonProperty("offer") OfferDetails offerDetails,
                         @JsonProperty("links") Map<String, Link> links) {
        super(links);
        this.offerDetails = offerDetails;
    }

    public OfferDetails getOfferDetails() {
        return offerDetails;
    }

}
