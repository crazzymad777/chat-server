package org.example.dto;

import lombok.Builder;
import lombok.Getter;
import org.example.repo.Messages;

import java.util.UUID;

@Builder
public class Chat {
    @Getter
    @Builder.Default
    private String type = "chat";
    static long total = 0;

    @Getter
    @Builder.Default
    final private String id = UUID.randomUUID().toString();

    @Getter
    @Builder.Default
    Messages repo = new Messages();

    @Getter
    @Builder.Default
    private Long number = ++total;

    @Getter
    @Builder.Default
    private String name = "Chat #" + total;
}
