package com.arun.controller;

import com.arun.models.Reels;
import com.arun.models.User;
import com.arun.service.ReelsService;
import com.arun.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ReelsController {

    @Autowired
    private ReelsService reelsService;

    @Autowired
    private UserService userService;


    @PostMapping("/api/reels")
    public Reels createReels(@RequestBody Reels reels, @RequestHeader("Authorization") String jwt){

        User reqUser = userService.findUserByJWT(jwt);
        Reels createdReels = reelsService.createReel(reels,reqUser);

        return createdReels;
    }


    @GetMapping("/api/reels")
    public List<Reels> findAllReels(){

        return reelsService.findAllReels();

    }

    @GetMapping("/api/reels/user/{userId}")
    public List<Reels> findReelsByUserId(@PathVariable("userId") Integer userId) throws Exception {
        List<Reels> findReels = reelsService.findReelsByUserReels(userId);
        return findReels;
    }





}
