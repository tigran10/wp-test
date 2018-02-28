package com.worldpay.test.oferrial.endpoints;

import com.worldpay.test.oferrial.app.Endpoints;
import com.worldpay.test.oferrial.app.OfferResource;
import com.worldpay.test.oferrial.data.OfferDao;
import com.worldpay.test.oferrial.data.OfferDaoInMemory;
import com.worldpay.test.oferrial.domain.ModerationStatus;
import com.worldpay.test.oferrial.domain.OfferDetails;
import com.worldpay.test.oferrial.exception.mapper.*;
import com.worldpay.test.oferrial.rest.OfferCreatedResponse;
import com.worldpay.test.oferrial.rest.OfferResponse;
import com.worldpay.test.oferrial.rest.SearchResponse;
import io.dropwizard.testing.junit.ResourceTestRule;
import jersey.repackaged.com.google.common.collect.ImmutableList;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Optional;

import static com.worldpay.test.oferrial.app.Endpoints.MODERATION_STATUS;
import static com.worldpay.test.oferrial.app.Endpoints.OFFERS_ENDPOINT_URL;
import static com.worldpay.test.oferrial.domain.ModerationStatus.REJECTED;
import static com.worldpay.test.oferrial.fixtures.OfferConstants.*;
import static com.worldpay.test.oferrial.rest.StandardLinks.STANDARD_LINKS;
import static io.dropwizard.testing.FixtureHelpers.fixture;
import static org.eclipse.jetty.http.HttpStatus.CREATED_201;
import static org.eclipse.jetty.http.HttpStatus.NOT_FOUND_404;
import static org.eclipse.jetty.http.HttpStatus.OK_200;
import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;


@RunWith(MockitoJUnitRunner.class)
public class OfferResourceTest {

    private static final OfferDao OFFER_DAO = mock(OfferDaoInMemory.class);


    @ClassRule
    public static final ResourceTestRule resources = ResourceTestRule.builder()
            .addResource(new OfferResource(OFFER_DAO))
            .addProvider(new RuntimeExceptionMapper())
            .addProvider(new EntityNotFoundExceptionMapper())
            .addProvider(new NotFoundExceptionMapper())
            .addProvider(new BadRequestExceptionMapper())
            .addProvider(new InvalidDefinitionExceptionMapper())
            .build();

    @Test
    public void getReturnsAllOffers() throws Exception {

        //given
        ImmutableList<OfferDetails> offers = ImmutableList
                .<OfferDetails>builder()
                .add(IPHONE)
                .add(SAMSUNG).build();
        given(OFFER_DAO.findAll()).willReturn(offers);

        //when
        SearchResponse response = resources.client()
                .target(OFFERS_ENDPOINT_URL)
                .request()
                .accept(MediaType.APPLICATION_JSON).get(SearchResponse.class);

        //then
        assertThat(response.getOffers()).containsOnly(IPHONE, SAMSUNG);
        assertThat(response.getLinks().entrySet()).containsAll(STANDARD_LINKS.getLinks().entrySet());

    }

    @Test
    public void getReturnsOneOffer() throws Exception {

        //given
        given(OFFER_DAO.findOfferById(IPHONE_ID)).willReturn(Optional.of(IPHONE));

        //when
        OfferResponse response = resources.client()
                .target(OFFERS_ENDPOINT_URL + "/" + IPHONE_ID.toIdString())
                .request()
                .accept(MediaType.APPLICATION_JSON).get(OfferResponse.class);

        //then
        assertThat(response.getOfferDetails()).isEqualsToByComparingFields(IPHONE);
        assertThat(response.getLinks().entrySet()).containsAll(STANDARD_LINKS.getLinks().entrySet());

    }

    @Test
    public void getReturns404WhenOfferNotFound() throws Exception {

        //given
        given(OFFER_DAO.findOfferById(IPHONE_ID)).willReturn(Optional.empty());

        //when
        Response response = resources.client()
                .target(OFFERS_ENDPOINT_URL + "/" + IPHONE_ID.toIdString())
                .request()
                .accept(MediaType.APPLICATION_JSON).get();

        //then
        assertThat(response.getStatus()).isEqualTo(NOT_FOUND_404);

    }


    @Test
    public void deleteReturns404WhenOfferNotFound() throws Exception {

        //given
        doNothing().when(OFFER_DAO).deleteOffer(IPHONE_ID);
        given(OFFER_DAO.findOfferById(IPHONE_ID)).willReturn(Optional.empty());

        //when
        Response response = resources.client()
                .target(OFFERS_ENDPOINT_URL + "/" + IPHONE_ID)
                .request()
                .accept(MediaType.APPLICATION_JSON).delete();

        //then
        assertThat(response.getStatus()).isEqualTo(NOT_FOUND_404);

    }


    @Test
    public void deleteWillDelete() throws Exception {

        //given
        doNothing().when(OFFER_DAO).deleteOffer(IPHONE_ID);
        given(OFFER_DAO.findOfferById(IPHONE_ID)).willReturn(Optional.of(IPHONE));

        //when
        Response response = resources.client()
                .target(OFFERS_ENDPOINT_URL + "/" + IPHONE_ID)
                .request()
                .accept(MediaType.APPLICATION_JSON).delete();

        //then
        assertThat(response.getStatus()).isEqualTo(OK_200);
        verify(OFFER_DAO, times(1)).deleteOffer(IPHONE_ID);

    }

    @Test
    public void postWillReturn201() throws Exception {

        //given
        given(OFFER_DAO.saveOffer(any())).willReturn(IPHONE);

        //when
        Response response = resources.client()
                .target(OFFERS_ENDPOINT_URL)
                .request()
                .accept(MediaType.APPLICATION_JSON)
                .post(Entity.json(fixture("fixtures/googlehome.json")));

        //then
        assertThat(response.getStatus()).isEqualTo(CREATED_201);


    }


    @Test
    public void putWillReturn404IfNotFound() throws Exception {

        //given
        given(OFFER_DAO.findOfferById(IPHONE_ID)).willReturn(Optional.empty());


        //when
        Response response = resources.client()
                .target(OFFERS_ENDPOINT_URL + "/" + IPHONE_ID.toIdString())
                .request()
                .accept(MediaType.APPLICATION_JSON)
                .put(Entity.json(fixture("fixtures/googlehome.json")));

        //then
        assertThat(response.getStatus()).isEqualTo(NOT_FOUND_404);

    }

    @Test
    public void putWillReturnResponse() throws Exception {

        //given
        given(OFFER_DAO.updateOffer(IPHONE)).willReturn(IPHONE);
        given(OFFER_DAO.findOfferById(IPHONE_ID)).willReturn(Optional.of(IPHONE));


        //when
        OfferResponse response = resources.client()
                .target(OFFERS_ENDPOINT_URL + "/" + IPHONE_ID.toIdString())
                .request()
                .accept(MediaType.APPLICATION_JSON)
                .put(Entity.json(fixture("fixtures/googlehome.json")), OfferResponse.class);

        //then
        assertThat(response.getOfferDetails()).isEqualTo(IPHONE);

    }

    @Test
    public void putModerationStatus() throws Exception {

        //given
        OfferDetails REJECTED_IPHONE = IPHONE.builder().withStatus(REJECTED).build();
        given(OFFER_DAO.updateOffer(REJECTED_IPHONE)).willReturn(REJECTED_IPHONE);
        given(OFFER_DAO.findOfferById(IPHONE_ID)).willReturn(Optional.of(IPHONE));

        String moderationUrl = OFFERS_ENDPOINT_URL + "/" + IPHONE_ID.toIdString() + MODERATION_STATUS;
        //when
        OfferResponse response = resources.client()
                .target(moderationUrl)
                .request()
                .accept(MediaType.APPLICATION_JSON)
                .put(Entity.json(REJECTED), OfferResponse.class);

        //then
        assertThat(response.getOfferDetails()).isEqualTo(REJECTED_IPHONE);

    }

}