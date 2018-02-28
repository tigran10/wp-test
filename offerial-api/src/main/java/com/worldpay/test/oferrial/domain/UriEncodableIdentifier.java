package com.worldpay.test.oferrial.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonValue;
import org.hibernate.validator.constraints.NotEmpty;

import java.net.URI;
import java.net.URISyntaxException;

public class UriEncodableIdentifier implements Identity, UriComponent {

    @JsonIgnore
    @NotEmpty
    private final String id;

    @JsonIgnore
    private URI uriComponent;

    @JsonCreator
    public UriEncodableIdentifier(String id) {
        this.id = id;
        uriComponent = tryUriEncode();
    }

    @JsonValue
    @Override
    public String toIdString() {
        return id;
    }

    @JsonIgnore
    @Override
    public URI toUriComponent() {
        return uriComponent;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Identity)) {
            return false;
        }
        Identity otherId = (Identity) obj;

        return toIdString().equals(otherId.toIdString());
    }

    @Override
    public int hashCode() {
        return toIdString().hashCode();
    }

    @Override
    public String toString() {
        return String.format("%s", id);
    }

    private URI tryUriEncode() {
        URI tryUriComponent;
        try {
            tryUriComponent = new URI(toIdString());
        } catch (URISyntaxException e) {
            throw new IllegalArgumentException("Could not encode id to URI component: " + id, e);
        }
        return tryUriComponent;
    }
}