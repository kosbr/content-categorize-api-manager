package com.github.kosbr.categorize.api.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Status {

    PENDING(0),
    READY(1),
    ERROR(2);

    private final int code;

    public static Status fromCode(int inputCode) {
        for (Status value : Status.values()) {
            if (value.getCode() == inputCode) {
                return value;
            }
        }
        throw new IllegalArgumentException("Unknown status " + inputCode);
    }
}
