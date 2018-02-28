package com.worldpay.test.oferrial.app;

import com.worldpay.test.oferrial.exception.mapper.BadRequestExceptionMapper;
import com.worldpay.test.oferrial.exception.mapper.EntityNotFoundExceptionMapper;
import com.worldpay.test.oferrial.exception.mapper.InvalidDefinitionExceptionMapper;
import com.worldpay.test.oferrial.exception.mapper.RuntimeExceptionMapper;
import io.dropwizard.setup.Environment;

final class MapperWiring {

    MapperWiring() {
    }

    void addTo(Environment environment) {
        environment.jersey().register(EntityNotFoundExceptionMapper.class);
        environment.jersey().register(BadRequestExceptionMapper.class);
        environment.jersey().register(InvalidDefinitionExceptionMapper.class);
        environment.jersey().packages(RuntimeExceptionMapper.class.getPackage().getName());
    }

}