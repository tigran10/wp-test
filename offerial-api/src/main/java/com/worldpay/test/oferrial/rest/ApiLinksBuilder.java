package com.worldpay.test.oferrial.rest;


import java.util.Collections;
import java.util.Map;

import static com.google.common.collect.Maps.newHashMap;

final public class ApiLinksBuilder {

    private final Map<String, Link> links = newHashMap();

    private ApiLinksBuilder() {
    }

    public ApiLinksBuilder add(String name, Link link) {
        links.put(name, link);
        return this;
    }

    public ApiLinksBuilder add(String name, String url) {
        return add(name, new Link(url, name));
    }

    public ApiLinksBuilder add(ApiLinks linksToAdd) {
        links.putAll(linksToAdd.getLinks());
        return this;
    }

    public ApiLinks build() {
        return new ApiLinks(Collections.unmodifiableMap(links));
    }

    public static ApiLinksBuilder newLinks() {
        return new ApiLinksBuilder();
    }

}