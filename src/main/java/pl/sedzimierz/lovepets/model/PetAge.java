package pl.sedzimierz.lovepets.model;

import com.fasterxml.jackson.annotation.JsonValue;

public enum PetAge {
    BABY("Baby"),
    YOUNG("Young"),
    ADULT("Adult"),
    SENIOR("Senior");

    private final String displayValue;

    PetAge(String displayValue) {
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
