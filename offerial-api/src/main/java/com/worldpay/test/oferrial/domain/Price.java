package com.worldpay.test.oferrial.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class Price extends ReflectionEqualsHashCodeToString {

    @JsonProperty
    @NotNull
    private BigDecimal value;

    @JsonProperty
    @NotNull
    private Currency currency;

    @JsonCreator
    public Price(@JsonProperty("value") BigDecimal value, @JsonProperty("currency") Currency currency) {
        this.value = value;
        this.currency = currency;
    }

    public BigDecimal getValue() {
        return value;
    }

    public Currency getCurrency() {
        return currency;
    }

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @NotNull
    public BigDecimal displayValue() {
        return value.setScale(2, RoundingMode.HALF_EVEN);
    }

}
