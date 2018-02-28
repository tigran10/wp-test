package com.worldpay.test.oferrial.app;

import com.worldpay.test.oferrial.data.OfferDao;
import com.worldpay.test.oferrial.data.OfferDaoInMemory;
import io.dropwizard.setup.Environment;

final class EndpointsWiring {

    private final OfferDao offerDao;

    private final OfferResource offerResource;

    EndpointsWiring() {
        offerDao = new OfferDaoInMemory();
        offerResource = new OfferResource(offerDao);
    }

    void addTo(Environment environment) {
        environment.jersey().register(BaseEndpoint.class);
        environment.jersey().register(offerResource);
    }

}