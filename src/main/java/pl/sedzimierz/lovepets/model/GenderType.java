package pl.sedzimierz.lovepets.model;

import com.fasterxml.jackson.annotation.JsonValue;

public enum GenderType {
    MALE("Male"),
    FEMALE("Female");

    private final String displayValue;

    GenderType(String displayValue) {
        this.displayValue = displayValue;
    }

    @JsonValue
    public String getDisplayValue() {
        return displayValue;
    }

    @Override
    public String toString() {
        return displayValue;
    }
}
