package com.worldpay.test.oferrial.domain;

import static com.worldpay.test.oferrial.domain.OfferDetails.Builder.start;
import static io.dropwizard.testing.FixtureHelpers.*;
import static org.assertj.core.api.Assertions.assertThat;
import io.dropwizard.jackson.Jackson;
import org.junit.Test;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.math.BigDecimal;
import java.time.LocalDate;


public class OfferDetailsTest {
    private static final ObjectMapper MAPPER = Jackson.newObjectMapper();

    @Test
    public void serializesToJSON() throws Exception {
        UniqueOfferId id = new UniqueOfferId("6adbee8d-dd37-4828-8656-8404a66680ef");

        OfferDetails offer = start(id,
                new Price(BigDecimal.valueOf(49.124), Currency.GBP),
                "google home",
                LocalDate.of(2018,2,27),
                LocalDate.of(2019,2,27)).build();

        final String expected = MAPPER.writeValueAsString(
                MAPPER.readValue(fixture("fixtures/googlehome.json"), OfferDetails.class));

        assertThat(MAPPER.writeValueAsString(offer)).isEqualTo(expected);
    }

    @Test
    public void deserializesFromJSON() throws Exception {

        UniqueOfferId id = new UniqueOfferId("6adbee8d-dd37-4828-8656-8404a66680ef");

        OfferDetails offer = start(id,
                new Price(BigDecimal.valueOf(49.124), Currency.GBP),
                "google home",
                LocalDate.of(2018,2,27),
                LocalDate.of(2019,2,27)).build();

        assertThat(MAPPER.readValue(fixture("fixtures/googlehome.json"), OfferDetails.class))
                .isEqualToComparingFieldByFieldRecursively(offer);
    }

    @Test
    public void isActiveTest() throws Exception {
        UniqueOfferId id = new UniqueOfferId("6adbee8d-dd37-4828-8656-8404a66680ef");

        OfferDetails valid = start(id,
                new Price(BigDecimal.valueOf(49.124), Currency.GBP),
                "google home",
                LocalDate.of(2016,1,27),
                LocalDate.now().plusDays(1)).build();

        assertThat(valid.isActive()).isTrue();


        OfferDetails expired = start(id,
                new Price(BigDecimal.valueOf(49.124), Currency.GBP),
                "google home",
                LocalDate.of(2016,1,27),
                LocalDate.of(2017,2,27)).build();

        assertThat(expired.isActive()).isFalse();


        OfferDetails notStarted = start(id,
                new Price(BigDecimal.valueOf(49.124), Currency.GBP),
                "google home",
                LocalDate.now().plusDays(2),
                LocalDate.now().plusDays(4)).build();

        assertThat(notStarted.isActive()).isFalse();


        OfferDetails rejected = start(id,
                new Price(BigDecimal.valueOf(49.124), Currency.GBP),
                "google home",
                LocalDate.of(2016,1,27),
                LocalDate.now().plusDays(1)).withStatus(ModerationStatus.REJECTED).build();

        assertThat(rejected.isActive()).isFalse();
    }

}