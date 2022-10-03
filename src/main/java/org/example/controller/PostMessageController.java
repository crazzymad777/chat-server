package org.example.controller;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.example.dto.Chat;
import org.example.dto.Data;
import org.example.dto.Message;
import org.example.dto.TextMessage;
import org.example.repo.Chats;
import org.example.repo.Messages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
public class PostMessageController {
    @Autowired
    private Chats chatRepo;

    @Autowired
    private SimpMessagingTemplate brokerMessagingTemplate;

    @Scheduled(fixedRate = 3000)
    public void fireGreeting() {
        fire();
    }

    public void fire() {
        System.out.println("Fire");
        brokerMessagingTemplate.convertAndSend("/channel", "Fire");
    }

    @PostMapping("/message/post_text_message")
    public ResponseEntity postTextMessage(@RequestParam(name = "chat_id") String chatId, @RequestParam(name = "text") String text) {
        var chat = chatRepo.findChat(chatId);
        if (chat == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("chat not found");
        }

        var message = TextMessage.builder().chat(chat).text(text).build();
        var messageRepo = chat.getRepo();
        messageRepo.push(message);

//        try {
//            send(new OutputMessage("1", "2"));
//        } catch (Exception e) {
//
//        }

        fire();

        return ResponseEntity.ok(message);
    }

    @GetMapping("/message")
    public String index() {
//        return new TextMessage("text");
        return "ok";
    }

    @GetMapping("/message/messages")
    public ResponseEntity messages(@RequestParam(name = "chat_id") String chatId) {
        var chat = chatRepo.findChat(chatId);
        if (chat == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("chat not found");
        }

        var messageRepo = chat.getRepo();
        return ResponseEntity.ok(messageRepo.getMessages());
    }

//    @GetMapping("/messages/chats")
//    public List<Chat> chats() {
//        return chatRepo.getChats();
//    }

//    @GetMapping("/data")
//    public Data data() {
//        return new Data("12345", "payload", true, false,  -25.3, 23);
//    }
}
