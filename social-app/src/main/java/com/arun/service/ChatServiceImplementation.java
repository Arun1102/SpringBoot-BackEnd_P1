package com.arun.service;

import com.arun.models.Chat;
import com.arun.models.User;
import com.arun.repository.ChatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ChatServiceImplementation implements ChatService{



    @Autowired
    private ChatRepository chatRepository;

    @Override
    public Chat createChat(User reqUser, User user2) {

        Chat isExist = chatRepository.findChatByUsersId(user2,reqUser);

        if(isExist != null){
            return isExist;
        }

        Chat chat = new Chat();
        chat.getUsers().add(user2);
        chat.getUsers().add(reqUser);
        chat.setTimeStamp(LocalDateTime.now());

        return chatRepository.save(chat);

//        return null;
    }

    @Override
    public Chat findChatByChatID(Integer chatId) throws Exception {
        Optional<Chat> chat = chatRepository.findById(chatId);

        if(chat.isEmpty()){
           throw new Exception("Chat not found with id " +  chatId);
        }

        return chat.get();
    }

    @Override
    public List<Chat> findUsersChat(Integer userID) {

        return chatRepository.findByUsersId(userID);
    }
}