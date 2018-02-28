package com.worldpay.test.oferrial.domain;

import java.net.URI;

public interface UriComponent {

    /**
     * Convert the ID to a form suitable for use as part of a URL.
     * <p>
     * E.g. "foo-bar", "foo/bar"
     * <p>
     * But not: "/foo-bar" or "foo-bar/"
     */
    URI toUriComponent();
}
