package com.worldpay.test.oferrial.endpoints;

import com.worldpay.test.oferrial.app.BaseEndpoint;
import com.worldpay.test.oferrial.app.Endpoints;
import com.worldpay.test.oferrial.exception.mapper.*;
import io.dropwizard.testing.junit.ResourceTestRule;
import org.junit.ClassRule;
import org.junit.Test;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static net.javacrumbs.jsonunit.fluent.JsonFluentAssert.assertThatJson;
import static org.eclipse.jetty.http.HttpStatus.NOT_FOUND_404;
import static org.fest.assertions.api.Assertions.assertThat;


public class BaseEndpointTest {

    @ClassRule
    public static final ResourceTestRule resources = ResourceTestRule.builder()
            .addResource(new BaseEndpoint())
            .addProvider(new RuntimeExceptionMapper())
            .addProvider(new EntityNotFoundExceptionMapper())
            .addProvider(new NotFoundExceptionMapper())
            .addProvider(new BadRequestExceptionMapper())
            .addProvider(new InvalidDefinitionExceptionMapper())
            .build();

    @Test
    public void getReturnsApiLinksToEnableServiceDiscovery() throws Exception {

        String response = resources.client()
                .target("/")
                .request()
                .accept(MediaType.APPLICATION_JSON).get(String.class);

        //Standard Links
        assertThatJson(response).node("links.offers.href").isEqualTo(Endpoints.OFFERS_ENDPOINT_URL);
        assertThatJson(response).node("links.offers.rel").isEqualTo("offers");
        assertThatJson(response).node("links.self.href").isEqualTo(Endpoints.BASE_URL);
        assertThatJson(response).node("links.self.rel").isEqualTo("self");

    }

    @Test
    public void returns404JsonResponseForUnknownUri() throws Exception {

        String doesNotExistUri = Endpoints.BASE_URL + "thisUriDoesNotExist";

        Response response = resources.client()
                .target(doesNotExistUri)
                .request()
                .accept(MediaType.APPLICATION_JSON).get();

        assertThat(response.getStatus()).isEqualTo(NOT_FOUND_404);
    }
}