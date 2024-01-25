package com.arun.service;

import com.arun.models.Reels;
import com.arun.models.User;
import com.arun.repository.ReelsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReelsServiceImplementation implements ReelsService{

    @Autowired
    private ReelsRepository reelsRepository;

    private UserService userService;




    @Override
    public Reels createReel(Reels reels, User user) {

        Reels createReel = new Reels();
        createReel.setUser(user);
        createReel.setTitle(reels.getTitle());
        createReel.setVideo(reels.getVideo());

        return reelsRepository.save(createReel);
    }

    @Override
    public List<Reels> findAllReels() {

        return reelsRepository.findAll();

    }

    @Override
    public List<Reels> findReelsByUserReels(Integer userId) throws Exception {

//        userService.findUserById(userId);



        return reelsRepository.findReelsByUserId(userId);

    }
}
