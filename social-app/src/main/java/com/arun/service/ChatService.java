package com.arun.service;

import com.arun.models.Chat;
import com.arun.models.User;

import java.util.List;

public interface ChatService {

    public Chat createChat(User reqUser, User user2);

    public Chat findChatByChatID(Integer chatId) throws Exception;

    public List<Chat> findUsersChat(Integer userID);

}
