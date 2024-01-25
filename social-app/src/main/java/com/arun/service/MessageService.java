package com.arun.service;

import com.arun.models.Chat;
import com.arun.models.Message;
import com.arun.models.User;

import java.util.List;

public interface MessageService {

    public Message createMessage(User user, Integer chatId, Message message) throws Exception;

    public List<Message> findChatsMessage(Integer chatId) throws Exception;




}
