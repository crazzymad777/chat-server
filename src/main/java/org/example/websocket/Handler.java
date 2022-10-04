package org.example.websocket;

import org.example.dto.Chat;
import org.example.dto.Message;
import org.example.repo.Chats;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.*;
import org.springframework.web.socket.handler.BinaryWebSocketHandler;

import java.io.IOException;
import java.util.HashMap;

@Component
public class Handler extends BinaryWebSocketHandler {
    @Autowired
    private Chats chatRepo;
    private final HashMap<String, Session> sessions = new HashMap<>();

    public void fire(Chat chat, Message message) {
        for (var pair : sessions.entrySet()) {
            if (pair.getValue().getChats().contains(chat)) {
                try {
                    pair.getValue().getWebSocketSession().sendMessage(new TextMessage(message.getId()));
                } catch (IOException ignored) {

                }
            }
        }
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) {
        try {
            var payload = message.getPayload();
            session.sendMessage(new TextMessage(payload));
            sessions.get(session.getId()).subscribe(payload, chatRepo);
        } catch (IOException ignored) {

        }
    }
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        super.afterConnectionEstablished(session);
        try {
            session.sendMessage(new TextMessage("Hello, world!"));
//            session.sendMessage(new TextMessage(Objects.requireNonNull(session.getId())));
            sessions.put(session.getId(), new Session(session));
        } catch (IOException ignored) {

        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        super.afterConnectionClosed(session, status);
    }
}
