package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class Data {
    @Getter
    private String id;

    @Getter
    private String payload;

    @Getter
    private boolean flag;

    @Getter
    private Boolean anotherFlag;

    @Getter
    private double floatValue;

    @Getter
    private int integerValue;
}
