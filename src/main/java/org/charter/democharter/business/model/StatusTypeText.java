package org.charter.democharter.business.model;

import com.fasterxml.jackson.annotation.JsonValue;

public enum StatusTypeText {
    SUCCESS("success"),
    FAILURE("failure");

    private final String value;

    StatusTypeText(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }
}
