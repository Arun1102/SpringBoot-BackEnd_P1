package com.arun.service;

import com.arun.models.Reels;
import com.arun.models.User;
import org.springframework.stereotype.Service;

import java.util.List;


public interface ReelsService {

    public Reels createReel(Reels reels, User user);

    public List<Reels> findAllReels();

    public List<Reels> findReelsByUserReels(Integer userId) throws Exception;
}
