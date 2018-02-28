package com.worldpay.test.oferrial.exception.mapper;

import com.fasterxml.jackson.databind.exc.InvalidDefinitionException;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
final public class InvalidDefinitionExceptionMapper implements ExceptionMapper<InvalidDefinitionException> {

    @Override
    public Response toResponse(InvalidDefinitionException e) {
        return Response.status(Status.BAD_REQUEST)
                .type(MediaType.APPLICATION_JSON_TYPE)
                .entity(new ErrorResponse(e.getMessage()))
                .build();
    }

}
