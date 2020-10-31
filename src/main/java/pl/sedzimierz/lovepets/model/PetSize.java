package pl.sedzimierz.lovepets.model;

import com.fasterxml.jackson.annotation.JsonValue;

public enum PetSize {
    SMALL("Small"),
    MEDIUM("Medium"),
    LARGE("Large"),
    EXTRA_LARGE("Extra large");

    private final String displayValue;

    PetSize(String displayValue) {
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
