package com.worldpay.test.oferrial.rest;


import com.worldpay.test.oferrial.app.Endpoints;

import java.net.URI;
import javax.ws.rs.core.UriInfo;

import static com.worldpay.test.oferrial.rest.ApiLinksBuilder.newLinks;


public final class StandardLinks {

    public static final ApiLinks STANDARD_LINKS = newLinks()
            .add("offers", Endpoints.OFFERS_ENDPOINT_URL)
            .build();

    public static final String SELF = "self";
    public static final String PARENT = "parent";

    private StandardLinks() {
        // Constants Class: do not instantiate
    }

    public static String buildRelativePathTo(URI subPath, UriInfo uriInfo) {
        return uriInfo.getRequestUriBuilder()
                .path(subPath.toString())
                .build()
                .toString();
    }

    public static String self(UriInfo uriInfo) {
        return uriInfo.getRequestUriBuilder().build().toString();
    }
}
