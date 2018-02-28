package com.worldpay.test.oferrial.fixtures;

import com.worldpay.test.oferrial.domain.Currency;
import com.worldpay.test.oferrial.domain.OfferDetails;
import com.worldpay.test.oferrial.domain.Price;
import com.worldpay.test.oferrial.domain.UniqueOfferId;

import java.math.BigDecimal;
import java.time.LocalDate;

import static com.worldpay.test.oferrial.domain.OfferDetails.Builder.start;

public final class OfferConstants {

    public static UniqueOfferId IPHONE_ID = new UniqueOfferId("6adbee8d-dd37-4828-8656-8404a66680ef");
    public static UniqueOfferId SAMSUNG_ID = new UniqueOfferId("6d91e500-7b54-4cff-a3e4-86499f42a066");
    public static UniqueOfferId GOOGLE_HOME_ID = new UniqueOfferId("a42281db-0890-4313-8c6b-19dc3ca07ab7");

    public static OfferDetails IPHONE = start(
            IPHONE_ID,
            new Price(BigDecimal.valueOf(10.124), Currency.GBP),
            "iphone 6s",
            LocalDate.now(),
            LocalDate.now()).build();

    public static OfferDetails SAMSUNG = OfferDetails
            .Builder
            .of(IPHONE)
            .withUniqueId(SAMSUNG_ID)
            .withName("samsung galaxy")
            .build();

    public static OfferDetails GOOGLE_HOME = OfferDetails
            .Builder
            .start(new UniqueOfferId("6adbee8d-dd37-4828-8656-8404a66680ef"),
                    new Price(new BigDecimal(49.124), Currency.GBP),
                    "google home",
                    LocalDate.of(2018,02,27),
                    LocalDate.of(2019,02,27))
            .build();

}
