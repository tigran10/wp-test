package com.worldpay.test.oferrial.exception;

public class BadRequestException extends RuntimeException {

    private static final long serialVersionUID = -4815364109672719801L;

    public BadRequestException(String message) {
        super(message);
    }

    public BadRequestException(Throwable cause) {
        super(cause);
    }

    public BadRequestException(String message, Throwable cause) {
        super(message, cause);
    }

}
