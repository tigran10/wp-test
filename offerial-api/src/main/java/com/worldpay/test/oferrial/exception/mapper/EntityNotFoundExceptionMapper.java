package com.worldpay.test.oferrial.exception.mapper;

import static org.eclipse.jetty.http.HttpStatus.NOT_FOUND_404;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.worldpay.test.oferrial.exception.OfferNotFoundException;

@Provider
public class EntityNotFoundExceptionMapper implements ExceptionMapper<OfferNotFoundException> {

    @Override
    public Response toResponse(OfferNotFoundException e) {
        return Response.status(NOT_FOUND_404)
                .type(MediaType.APPLICATION_JSON_TYPE)
                .entity(new ErrorResponse(e.getMessage()))
                .build();
    }

}