package de.helpernet.pokopr.integration.teamdrive.client.dto;

public enum SpaceStatus {

    ACTIVE("active"),

    ARCHIVED("archived");

    private final String externalValue;

    SpaceStatus(String externalValue) {
        this.externalValue = externalValue;
    }

    public static SpaceStatus fromExternalValue(String externalValue) {

        return SpaceStatus.valueOf(SpaceStatus.class, externalValue.toUpperCase());
    }

    public String getExternalValue() {
        return externalValue;
    }
}
