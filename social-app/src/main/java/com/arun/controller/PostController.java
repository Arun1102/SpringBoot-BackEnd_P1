package com.arun.controller;

import com.arun.models.Post;
import com.arun.models.User;
import com.arun.response.ApiResponse;
import com.arun.service.PostService;
import com.arun.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PostController {

    @Autowired
    PostService postService;

    @Autowired
    UserService userService;

    @PostMapping("/api/posts")
    public ResponseEntity<Post> createPost(@RequestHeader("Authorization") String jwt,
                                           @RequestBody Post post
                                           ) throws Exception {
        User reqUser = userService.findUserByJWT(jwt);
        Post posts = postService.createNewPost(post,reqUser.getId());

        return new ResponseEntity<>(posts, HttpStatus.CREATED);
    }

    @GetMapping("/posts")
    public List<Post> findAllPost(){

        List<Post> posts = postService.findAllPost();
        return posts;
    }

    @DeleteMapping("/posts/{postId}")
    public ResponseEntity<ApiResponse> deletePost(@RequestHeader("Authorization") String jwt,@PathVariable Integer postId) throws Exception {

        User reqUser = userService.findUserByJWT(jwt);

        String value =  postService.deletePost(postId,reqUser.getId());
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

    @PutMapping("/posts/save/{postId}")
    public ResponseEntity<Post> savedPostHandler(@RequestHeader("Authorization") String jwt,
                                                 @PathVariable Integer postId) throws Exception {
        User reqUser = userService.findUserByJWT(jwt);
        Post posts = postService.savedPost(postId,reqUser.getId());
        return new ResponseEntity<>(posts,HttpStatus.ACCEPTED);
    }


    @PutMapping("/posts/like/{postId}")
    public ResponseEntity<Post> likePostHandler(@RequestHeader("Authorization") String jwt,
                                                @PathVariable Integer postId) throws Exception {

        User reqUser = userService.findUserByJWT(jwt);
        Post posts = postService.likedPost(postId, reqUser.getId());
        return new ResponseEntity<>(posts,HttpStatus.ACCEPTED);
    }






}
