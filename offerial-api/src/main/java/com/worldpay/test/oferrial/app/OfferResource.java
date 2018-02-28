package com.worldpay.test.oferrial.app;

import com.worldpay.test.oferrial.data.OfferDao;
import com.worldpay.test.oferrial.domain.ModerationStatus;
import com.worldpay.test.oferrial.domain.OfferDetails;
import com.worldpay.test.oferrial.domain.UniqueOfferId;
import com.worldpay.test.oferrial.exception.OfferNotFoundException;
import com.worldpay.test.oferrial.rest.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;

import static com.worldpay.test.oferrial.rest.ApiLinksBuilder.newLinks;
import static com.worldpay.test.oferrial.app.Endpoints.BASE_URL;
import static com.worldpay.test.oferrial.app.Endpoints.OFFERS_ENDPOINT_URL;
import static com.worldpay.test.oferrial.rest.StandardLinks.*;
import static java.lang.String.format;
import static org.eclipse.jetty.http.HttpStatus.CREATED_201;

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Api(OFFERS_ENDPOINT_URL)
@Path(OFFERS_ENDPOINT_URL)
public class OfferResource {

    private static final Logger logger = LoggerFactory.getLogger(OfferResource.class);

    private OfferDao offerDao;

    public OfferResource(@Context OfferDao offerDao) {
        this.offerDao = offerDao;
    }


    @POST
    @ApiOperation(value = "Add new offer", response = OfferCreatedResponse.class)
    public Response addNewOffer(
            @NotNull @Valid OfferDetails offerDetails,
            @Context UriInfo uriInfo) throws URISyntaxException {

        logger.info("Add new entity: {}", offerDetails);
        offerDao.saveOffer(offerDetails);

        URI newOfferLocation = offerDetails.getUniqueId().toUriComponent();


        ApiLinks links = newLinks()
                .add(STANDARD_LINKS)
                .add(SELF, buildRelativePathTo(newOfferLocation, uriInfo))
                .add(PARENT, BASE_URL)
                .build();

        OfferCreatedResponse response = new OfferCreatedResponse(offerDetails.getUniqueId(), links.getLinks());

        return Response
                .status(CREATED_201)
                .location(newOfferLocation)
                .entity(response)
                .build();
    }

    @GET
    @ApiOperation(value = "Get All")
    public SearchResponse findAll(@Context UriInfo uriInfo) {

        Collection<OfferDetails> offerDetails = offerDao.findAll();

        ApiLinks links = newLinks()
                .add(STANDARD_LINKS)
                .add(SELF, OFFERS_ENDPOINT_URL)
                .add(PARENT, BASE_URL)
                .build();

        return new SearchResponse(offerDetails, links.getLinks());
    }


    @GET
    @ApiOperation(value = "Get by ID")
    @Path("{id}")
    public OfferResponse findEntityById(
            @PathParam("id") UniqueOfferId id,
            @Context UriInfo uriInfo) {

        logger.info("Find offer by id: {}", id.toIdString());

        OfferDetails result = loadOfferOrReturn404(id);

        ApiLinksBuilder links = getLinks(id, uriInfo);

        return new OfferResponse(result, links);
    }


    @PUT
    @ApiOperation(value = "Update")
    @Path("{id}")
    public OfferResponse update(
            @PathParam("id") UniqueOfferId id,
            @NotNull @Valid OfferDetails entity,
            @Context UriInfo uriInfo) {

        logger.info("Update offer: {} with: ", id, entity);
        loadOfferOrReturn404(id);
        OfferDetails updatedOffer = offerDao.updateOffer(entity);

        return new OfferResponse(updatedOffer, getLinks(id, uriInfo));
    }


    @DELETE
    @ApiOperation(value = "Delete")
    @Path("{id}")
    public Response delete(
            @PathParam("id") UniqueOfferId id,
            @Context UriInfo uriInfo) {

        logger.info("Delete entity by id: {}", id);

        offerDao.deleteOffer(loadOfferOrReturn404(id).getUniqueId());

        final ApiLinks links = getLinks(id, uriInfo).build();

        return Response.ok(links).build();
    }

    @PUT
    @ApiOperation(value = "change moderation status")
    @Path("{id}" + Endpoints.MODERATION_STATUS)
    public OfferResponse moderationStatus(
            @PathParam("id") UniqueOfferId id,
            @NotNull ModerationStatus status,
            @Context UriInfo uriInfo) {

        OfferDetails offer = loadOfferOrReturn404(id);

        logger.info("Update word: {} with: ", id, offer);

        OfferDetails updatedEntity = offerDao.updateOffer(offer.builder().withStatus(status).build());

        return new OfferResponse(updatedEntity, getLinks(id, uriInfo));

    }

    private OfferDetails loadOfferOrReturn404(UniqueOfferId id) {
        return offerDao.findOfferById(id).orElseThrow(
                () -> new OfferNotFoundException(format("The requested offer does not exist: %s", id.toUriComponent()))
        );
    }

    private ApiLinksBuilder getLinks(UniqueOfferId id, UriInfo uriInfo) {

        String selfUri = uriInfo.getRequestUriBuilder().build().toString();
        String moderationStatus = selfUri + Endpoints.MODERATION_STATUS;

        return ApiLinksBuilder.newLinks()
                .add(STANDARD_LINKS)
                .add(PARENT, BASE_URL)
                .add(SELF, selfUri)
                .add("moderation-status", moderationStatus);
    }

}