package org.example.service;

import org.example.dto.Chat;
import org.example.dto.TextMessage;
import org.example.repo.Chats;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class ChatService {
    @Autowired
    private Chats chatRepo;

    @PostConstruct
    void init() {
        var chat = Chat.builder().id("376851d4-5006-4cc1-8289-277068ed228a").name("Default chat").build();
        chatRepo.push(chat);
        chat.getRepo().push(TextMessage.builder().chat(chat).text("Welcome to default chat!").build());
    }
}
