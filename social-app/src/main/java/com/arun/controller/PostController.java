package com.arun.controller;

import com.arun.models.Post;
import com.arun.response.ApiResponse;
import com.arun.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PostController {

    @Autowired
    PostService postService;

    @PostMapping("/posts/user/{userId}")
    public ResponseEntity<Post> createPost(@RequestBody Post post,@PathVariable("userId") Integer userId) throws Exception {

        Post posts = postService.createNewPost(post,userId);

        return new ResponseEntity<>(posts, HttpStatus.CREATED);
    }

    @GetMapping("/posts")
    public List<Post> findAllPost(){

        List<Post> posts = postService.findAllPost();
        return posts;
    }

    @DeleteMapping("/posts/{postId}/user/{userId}")
    public ResponseEntity<ApiResponse> deletePost(@PathVariable Integer postId, @PathVariable Integer userId) throws Exception {

        String value =  postService.deletePost(postId,userId);
        ApiResponse res = new ApiResponse(value,true);
        return new ResponseEntity<>(res,HttpStatus.OK);
    }

    @GetMapping("/posts/{postId}")
    public ResponseEntity<Post> findPostByIdHandler(@PathVariable Integer postId) throws Exception {

        Post post = postService.findPostByPostId(postId);

        return new ResponseEntity<>(post,HttpStatus.ACCEPTED);
    }

    @GetMapping("/posts/user/{userId}")
    public ResponseEntity<List<Post>> findUsersPost(@PathVariable Integer userId){

        List<Post> posts = postService.findPostByUserId(userId);
        return new ResponseEntity<>(posts,HttpStatus.OK);
    }

    @PutMapping("/posts/save/{postId}/user/{userId}")
    public ResponseEntity<Post> savedPostHandler(@PathVariable Integer postId,@PathVariable Integer userId) throws Exception {

        Post posts = postService.savedPost(postId,userId);
        return new ResponseEntity<>(posts,HttpStatus.ACCEPTED);
    }


    @PutMapping("/posts/like/{postId}/user/{userId}")
    public ResponseEntity<Post> likePostHandler(@PathVariable Integer postId,@PathVariable Integer userId) throws Exception {

        Post posts = postService.likedPost(postId,userId);
        return new ResponseEntity<>(posts,HttpStatus.ACCEPTED);
    }






}
