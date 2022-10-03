package org.example.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.annotation.PostConstruct;
import java.util.UUID;

@SuperBuilder
public class Message {
    @Getter
    @Builder.Default
    private String type = "message";

    @Getter
    final private String id = UUID.randomUUID().toString();

    @Getter
    final private long timestamp = System.currentTimeMillis();

    @NonNull
    final private Chat chat;

    @Getter
    @Setter
    private long count;
}
