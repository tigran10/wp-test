package com.worldpay.test.oferrial.rest;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.worldpay.test.oferrial.domain.OfferDetails;

import java.util.Collection;
import java.util.Map;

@JsonInclude(Include.NON_NULL)
public class SearchResponse extends AbstractResponseWithLinks {

    @JsonProperty
    private final Collection<OfferDetails> offers;

    public SearchResponse(Collection<OfferDetails> offers, ApiLinksBuilder links) {
        super(links);
        this.offers = offers;
    }

    @JsonCreator
    public SearchResponse(@JsonProperty("offers") Collection<OfferDetails> offers,
                          @JsonProperty("links") Map<String, Link> links) {
        super(links);
        this.offers = offers;
    }

    public Collection<OfferDetails> getOffers() {
        return offers;
    }

}
