package io.softwarestrategies.projectx.ui.data.enums;

public enum ProjectStatus {
    NEW("N"),
    PENDING("P"),
    ACTIVE("A"),
    INACTIVE("I"),
    OPEN("O"),
    CLOSED("C"),
    DELETED("D");

    private final String abbreviation;

    ProjectStatus(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public String getAbbreviation() {
        return this.abbreviation;
    }

    public static ProjectStatus fromAbbreviation(String abbr) {
        for (ProjectStatus status : ProjectStatus.values()) {
            if (status.abbreviation.equalsIgnoreCase(abbr)) {
                return status;
            }
        }
        throw new UnsupportedOperationException("This ProjectStatus abbreviation is not supported: " + abbr);
    }
}
