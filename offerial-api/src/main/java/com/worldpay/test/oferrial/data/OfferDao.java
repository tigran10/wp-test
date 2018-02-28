package com.worldpay.test.oferrial.data;

import com.worldpay.test.oferrial.domain.OfferDetails;
import com.worldpay.test.oferrial.domain.UniqueOfferId;

import java.util.Collection;
import java.util.Optional;

public interface OfferDao {
    OfferDetails saveOffer(OfferDetails offerDetails);

    OfferDetails updateOffer(OfferDetails offerDetails);

    void deleteOffer(UniqueOfferId offerId);

    Optional<OfferDetails> findOfferById(UniqueOfferId offerId);

    Collection<OfferDetails> findAll();
}
