package com.worldpay.test.oferrial.domain;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class ReflectionEqualsHashCodeToString {

    @SuppressWarnings("static-method")
    protected String[] ignoreFields() {
        return new String[]{};
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this, ignoreFields());
    }

    @Override
    public boolean equals(final Object obj) {
        return obj != null && EqualsBuilder.reflectionEquals(this, obj, ignoreFields());
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

}