package com.arun.controller;

import com.arun.models.Comment;
import com.arun.models.User;
import com.arun.service.CommentService;
import com.arun.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private UserService userService;

    @PostMapping("/api/comments/post/{postId}")
    public Comment createComment(@RequestBody Comment comment,
                                 @RequestHeader("Authorization") String jwt,
                                 @PathVariable("postId") Integer postId) throws Exception {
        User user = userService.findUserByJWT(jwt);
        Comment createdComment = commentService.createComment(comment,postId,user.getId());
        return createdComment;
    }

    @PutMapping("/api/comments/like/{commentId}")
    public Comment likeComment(
                                 @RequestHeader("Authorization") String jwt,
                                 @PathVariable("commentId") Integer commentId) throws Exception {
        User user = userService.findUserByJWT(jwt);
        Comment likedComment = commentService.likeComment(commentId,user.getId());
        return likedComment;
    }

}
