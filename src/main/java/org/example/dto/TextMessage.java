package org.example.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
public class TextMessage extends Message {
    @Getter
    @Builder.Default
    private String type = "text_message";

    @Getter
    private String text;
}
