package org.example.websocket;

import lombok.Getter;
import org.example.dto.Chat;
import org.example.repo.Chats;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.socket.WebSocketSession;

import java.util.ArrayList;

public class Session {
    @Getter
    private WebSocketSession webSocketSession;
    public Session(WebSocketSession session) {
        this.webSocketSession = session;
    }
    @Getter
    private final ArrayList<Chat> chats = new ArrayList<>();

    boolean subscribe(String chatId, Chats chatRepo) {
        var chat = chatRepo.findChat(chatId);
        if (chat == null) {
            return false;
        }
        if (!chats.contains(chat)) {
            chats.add(chat);
        }
        return true;
    }
}
