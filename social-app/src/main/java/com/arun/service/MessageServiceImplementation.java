package com.arun.service;

import com.arun.models.Chat;
import com.arun.models.Message;
import com.arun.models.User;
import com.arun.repository.ChatRepository;
import com.arun.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


@Service
public class MessageServiceImplementation implements MessageService{

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private ChatService chatService;

    @Autowired
    private ChatRepository chatRepository;
    @Override
    public Message createMessage(User user, Integer chatId, Message message) throws Exception {

        Message messages = new Message();

        Chat chat = chatService.findChatByChatID(chatId);

        messages.setChat(chat);
        messages.setContent(message.getContent());
        messages.setImage(message.getImage());
        messages.setUser(user);
        messages.setTimeStamp(LocalDateTime.now());

        Message savedMessage = messageRepository.save(messages);

        chat.getMessages().add(savedMessage);
        chatRepository.save(chat);

        return savedMessage;
    }

    @Override
    public List<Message> findChatsMessage(Integer chatId) throws Exception {

       Chat chat = chatService.findChatByChatID(chatId);

        return messageRepository.findByChatId(chat.getId());
    }
}
