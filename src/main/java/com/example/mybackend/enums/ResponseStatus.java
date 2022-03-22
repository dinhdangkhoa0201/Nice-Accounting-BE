package com.example.mybackend.enums;

import lombok.Getter;

@Getter
public enum ResponseStatus {
    SUCCESS("1", "Success"),
    FAILURE("0", "Failure"),
    NULL("", "");

    private final String code;
    private final String desc;

    private ResponseStatus(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
