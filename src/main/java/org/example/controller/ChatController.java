package org.example.controller;

import org.example.dto.Chat;
import org.example.repo.Chats;
import org.example.repo.Messages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ChatController {
    @Autowired
    private Chats chatRepo;

    @GetMapping("/chat/create")
    public Chat create() {
        var chat = Chat.builder().build();
        chatRepo.push(chat);
        return chat;
    }

    @GetMapping("/chat/chats")
    public List<Chat> chats() {
        return chatRepo.getChats();
    }
}
