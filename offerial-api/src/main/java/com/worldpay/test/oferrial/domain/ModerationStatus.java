package com.worldpay.test.oferrial.domain;

import com.fasterxml.jackson.annotation.JsonCreator;

import static java.lang.String.format;

public enum ModerationStatus {

    PENDING("pending"),
    APPROVED("approved"),
    REJECTED("rejected");

    private final String status;

    ModerationStatus(String status) {
        this.status = status;
    }

    public String value() {
        return status;
    }

    @JsonCreator
    public static ModerationStatus forName(String name) {
        ModerationStatus[] values = values();
        for (ModerationStatus moderationStatus : values) {
            if (moderationStatus.status.equalsIgnoreCase(name)) {
                return moderationStatus;
            }
        }

        throw new RuntimeException(format("Unknown ModerationStatus for value %s", name));
    }
}
