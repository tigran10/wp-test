package com.worldpay.test.oferrial.app;


import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.federecio.dropwizard.swagger.SwaggerBundle;
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OfferialApp extends Application<OfferialAppConfiguration> {

    private static final Logger LOGGER = LoggerFactory.getLogger(OfferialAppConfiguration.class);

    public static void main(String... args) throws Exception {
        new OfferialApp().run(args);
    }

    @Override
    public void initialize(Bootstrap<OfferialAppConfiguration> bootstrap) {
        bootstrap.addBundle(new SwaggerBundle<OfferialAppConfiguration>() {
            @Override
            protected SwaggerBundleConfiguration getSwaggerBundleConfiguration(OfferialAppConfiguration sampleConfiguration) {
                return sampleConfiguration.swaggerBundleConfiguration;
            }
        });
    }

    @Override
    public void run(OfferialAppConfiguration configuration, Environment environment) throws Exception {
        LOGGER.info("Starting offerial app");

        new EndpointsWiring().addTo(environment);
        new MapperWiring().addTo(environment);

    }

}
