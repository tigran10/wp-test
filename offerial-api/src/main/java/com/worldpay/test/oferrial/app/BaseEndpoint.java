package com.worldpay.test.oferrial.app;


import com.worldpay.test.oferrial.rest.ApiLinks;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static com.worldpay.test.oferrial.rest.ApiLinksBuilder.newLinks;
import static com.worldpay.test.oferrial.app.Endpoints.BASE_URL;
import static com.worldpay.test.oferrial.rest.StandardLinks.SELF;
import static com.worldpay.test.oferrial.rest.StandardLinks.STANDARD_LINKS;

@Path(BASE_URL)
@Api(BASE_URL)
final public class BaseEndpoint {

    @GET
    @ApiOperation(value = "Service discovery links", response = ApiLinks.class)
    @Produces(MediaType.APPLICATION_JSON)
    public Response serviceDiscoveryLinks() {
        ApiLinks links = newLinks()
                .add(SELF, BASE_URL)
                .add(STANDARD_LINKS)
                .build();

        return Response
                .ok(links)
                .build();
    }

}
