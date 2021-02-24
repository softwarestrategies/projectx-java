package io.softwarestrategies.projectx.ui.data.enums;

public enum Status {
    NEW("N"),
    PENDING("P"),
    ACTIVE("A"),
    INACTIVE("I"),
    OPEN("O"),
    CLOSED("C"),
    DELETED("D");

    private final String abbreviation;

    Status(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public String getAbbreviation() {
        return this.abbreviation;
    }

    public static Status fromAbbreviation(String abbr) {
        for (Status status : Status.values()) {
            if (status.abbreviation.equalsIgnoreCase(abbr)) {
                return status;
            }
        }
        throw new UnsupportedOperationException("This ProjectStatus abbreviation is not supported: " + abbr);
    }

    public static Status fromName(String name) {
        for (Status status : Status.values()) {
            if (status.name().equalsIgnoreCase(name)) {
                return status;
            }
        }
        throw new UnsupportedOperationException("This ProjectStatus name is not supported: " + name);
    }
}
