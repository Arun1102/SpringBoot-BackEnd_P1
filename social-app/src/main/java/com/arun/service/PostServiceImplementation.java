package com.arun.service;

import com.arun.models.Post;
import com.arun.models.User;
import com.arun.repository.PostRepository;
import com.arun.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Service
public class PostServiceImplementation implements PostService {

    @Autowired
    PostRepository postRepo;

    @Autowired
    UserService userService ;

    @Autowired
    UserRepository userRepo;

    @Override
    public Post createNewPost(Post post, Integer userId) throws Exception {

        User user = userService.findUserById(userId);
        Post newPost = new Post();

        newPost.setImage(post.getImage());
        newPost.setCaption(post.getCaption());
        newPost.setImage(post.getImage());
        newPost.setVideo(post.getVideo());
        newPost.setUser(user);
        newPost.setCreatedAt(LocalDateTime.now());

        Post savedPost = postRepo.save(newPost);
        return savedPost;

    }

    @Override
    public String deletePost(Integer postId, Integer userId) throws Exception {

        Post post = findPostByPostId(postId);
        User user = userService.findUserById(userId);

        if(post.getUser().getId() != user.getId()){
            throw new Exception("You can't delete other user's post!");
        }
        postRepo.delete(post);

        return "Post deleted Succesfully !";
    }

    @Override
    public List<Post> findPostByUserId(Integer userId) {

        List<Post> post = postRepo.findPostByUserId(userId);
        return post;
    }

    @Override
    public Post findPostByPostId(Integer postId) throws Exception {

        Optional<Post> post = postRepo.findById(postId);
        if(post.isEmpty()){
            throw new Exception("Post not found with postID " + postId);
        }

        Post findPost = post.get();

        return findPost;
    }

    @Override
    public List<Post> findAllPost() {

       List<Post> post =  postRepo.findAll();

        return post;
    }

    @Override
    public Post savedPost(Integer postId, Integer userId) throws Exception {
        Post post = findPostByPostId(postId);
        User user = userService.findUserById(userId);

        if(user.getSavedPost().contains(post)){
            user.getSavedPost().remove(post);
        }else{
            user.getSavedPost().add(post);
        }

        userRepo.save(user);

        return post;
    }

    @Override
    public Post likedPost(Integer postId, Integer userId) throws Exception {

        Post post = findPostByPostId(postId);
        User user = userService.findUserById(userId);

        if(post.getLiked().contains(user)){
            post.getLiked().remove(user);
        }else{
            post.getLiked().add(user);
        }

        Post posted = postRepo.save(post);

        return posted;
    }
}
