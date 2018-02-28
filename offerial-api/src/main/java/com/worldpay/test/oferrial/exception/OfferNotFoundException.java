package com.worldpay.test.oferrial.exception;

@SuppressWarnings("serial")
public class OfferNotFoundException extends RuntimeException {

    public OfferNotFoundException(String message) {
        super(message);
    }

    public OfferNotFoundException(Throwable cause) {
        super(cause);
    }

    public OfferNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

}
