package com.arun.repository;

import com.arun.models.Reels;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReelsRepository extends JpaRepository<Reels,Integer> {

    public List<Reels> findReelsByUserId(Integer userId);



}
