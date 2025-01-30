package com.ecc.ewhascholarship.domain.enums;

public enum ScholarshipType {
    TUITION_DISCOUNT("학비 감면"),
    ACADEMIC_ASSISTANCE("학업 보조비"),
    BOTH("학비 감면 + 학업 보조비");

    private final String displayName;

    ScholarshipType(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }
}
