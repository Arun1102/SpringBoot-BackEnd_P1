package com.arun.service;


import com.arun.models.Comment;

public interface CommentService {

    public Comment createComment(Comment comment,Integer postId,Integer userId) throws Exception;

    public Comment likeComment(Integer commentId,Integer userId) throws Exception;

    public Comment findCommentByID(Integer commentId) throws Exception;

}
