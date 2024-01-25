package com.arun.controller;

import com.arun.models.Chat;
import com.arun.models.User;
import com.arun.request.CreateChatRequest;
import com.arun.service.ChatService;
import com.arun.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ChatController {


    @Autowired
    private ChatService chatService;

    @Autowired
    private UserService userService;

    @PostMapping("api/chats")
    public Chat createChat(@RequestHeader("Authorization") String jwt,@RequestBody CreateChatRequest req) throws Exception {
        User reqUser = userService.findUserByJWT(jwt);
        User user2 = userService.findUserById(req.getUserId());
        Chat chat = chatService.createChat(reqUser,user2);
        return chat;
    }


    @GetMapping("/api/chats")
    public List<Chat> findUsersChat(@RequestHeader("Authorization") String jwt){

        User user = userService.findUserByJWT(jwt);
        List<Chat> chat = chatService.findUsersChat(user.getId());
        return chat;
    }


}
