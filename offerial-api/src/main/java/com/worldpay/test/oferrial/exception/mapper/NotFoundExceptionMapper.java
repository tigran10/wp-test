package com.worldpay.test.oferrial.exception.mapper;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import javax.ws.rs.NotFoundException;

@Provider
public class NotFoundExceptionMapper implements ExceptionMapper<NotFoundException> {

    @Override
    public Response toResponse(NotFoundException e) {
        return Response.status(e.getResponse().getStatus())
                .type(MediaType.APPLICATION_JSON_TYPE)
                .entity(new ErrorResponse("Error 404 - URL Not Found"))
                .build();
    }

}
