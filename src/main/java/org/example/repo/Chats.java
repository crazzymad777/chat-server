package org.example.repo;

import lombok.Getter;
import org.example.dto.Chat;
import org.example.dto.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public class Chats {
    @Getter
    private final ArrayList<Chat> chats = new ArrayList<>();

    public void push(Chat chat) {
        chats.add(chat);
    }

//    public Messages findMessageRepo(String chatId) {
//        return findChat(chatId).getRepo();
//    }

    public Chat findChat(String chatId) {
        var value = getChats().stream().filter(x -> x.getId().equals(chatId)).findFirst();
        return value.orElse(null);
    }
}
