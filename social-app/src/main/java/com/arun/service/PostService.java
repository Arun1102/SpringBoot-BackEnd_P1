package com.arun.service;


import com.arun.models.Post;

import java.util.List;

public interface PostService {

    public Post createNewPost(Post post , Integer userId) throws Exception;

    public String deletePost(Integer postId, Integer userId) throws Exception;

    public List<Post> findPostByUserId(Integer userId);
    public Post findPostByPostId(Integer postId) throws Exception;

    public List<Post> findAllPost();

    public Post savedPost(Integer postId,Integer userId) throws Exception;

    public Post likedPost(Integer postId,Integer userId) throws Exception;

}
