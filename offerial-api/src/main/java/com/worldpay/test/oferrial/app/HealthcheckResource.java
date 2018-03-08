package com.worldpay.test.oferrial.app;

import com.worldpay.test.oferrial.domain.UniqueOfferId;
import com.worldpay.test.oferrial.rest.ApiLinks;
import com.worldpay.test.oferrial.rest.ApiLinksBuilder;
import com.worldpay.test.oferrial.rest.SearchResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import static com.worldpay.test.oferrial.app.Endpoints.BASE_URL;
import static com.worldpay.test.oferrial.app.Endpoints.UPTIME_ENDPOINT_URL;
import static com.worldpay.test.oferrial.rest.ApiLinksBuilder.newLinks;
import static com.worldpay.test.oferrial.rest.StandardLinks.*;

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Api(UPTIME_ENDPOINT_URL)
@Path(UPTIME_ENDPOINT_URL)
public class HealthcheckResource {

    private static final Logger logger = LoggerFactory.getLogger(HealthcheckResource.class);


    @GET
    @ApiOperation(value = "up")
    public Response up(@Context UriInfo uriInfo) {


        ApiLinks links = newLinks()
                .add(SELF, BASE_URL)
                .add(STANDARD_LINKS)
                .build();

        return Response
                .ok(links)
                .build();
    }


}