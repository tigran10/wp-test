package com.worldpay.test.oferrial.data;

import com.worldpay.test.oferrial.domain.Currency;
import com.worldpay.test.oferrial.domain.OfferDetails;
import com.worldpay.test.oferrial.domain.Price;
import com.worldpay.test.oferrial.domain.UniqueOfferId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

import static com.worldpay.test.oferrial.domain.OfferDetails.Builder.start;
import static java.util.Optional.of;

//simple mock in memory dao implementation
public class OfferDaoInMemory implements OfferDao {

    private Map<UniqueOfferId, OfferDetails> data = new HashMap<>();

    private static final Logger logger = LoggerFactory.getLogger(OfferDaoInMemory.class);

    public OfferDaoInMemory() {
        //initial data
        UniqueOfferId id = new UniqueOfferId("6adbee8d-dd37-4828-8656-8404a66680ef");

        data.put(id, start(id,
                new Price(BigDecimal.valueOf(10.124), Currency.GBP),
                "iphone 6s",
                LocalDate.now(),
                LocalDate.now()).build()
        );
    }

    public OfferDetails saveOffer(OfferDetails offerDetails) {
        logger.info("save offer {}", offerDetails);

        OfferDetails udpatedOffer = offerDetails
                .builder()
                .withCreatedDate(of(LocalDate.now())).build();
        data.put(offerDetails.getUniqueId(), udpatedOffer);

        return offerDetails;
    }

    ;

    public OfferDetails updateOffer(OfferDetails offerDetails) {
        logger.info("udpate offer {}", offerDetails);

        OfferDetails udpatedOffer = offerDetails.builder().withUpdatedDate(of(LocalDate.now())).build();
        data.remove(offerDetails.getUniqueId());
        data.put(offerDetails.getUniqueId(), udpatedOffer);

        return offerDetails;
    }

    ;

    public void deleteOffer(UniqueOfferId offerId) {
        logger.info("delete offer {}", offerId);

        data.remove(offerId);
    }

    ;

    public Optional<OfferDetails> findOfferById(UniqueOfferId offerId) {
        logger.info("find offer {}", offerId);

        return Optional.ofNullable(data.get(offerId));
    }

    @Override
    public Collection<OfferDetails> findAll() {
        return data.values();
    }
}
