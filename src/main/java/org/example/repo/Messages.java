package org.example.repo;

import lombok.Getter;
import org.example.dto.Message;

import java.util.ArrayList;

public class Messages {
    private long count = 0;
    @Getter
    private final ArrayList<Message> messages = new ArrayList<>();

    public void push(Message message) {
        message.setCount(++count);
        messages.add(message);
    }
}
