package com.arun.controller;

import com.arun.models.User;
import com.arun.repository.UserRepository;
import com.arun.service.UserServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class UserController {

    @Autowired
    UserRepository userRepo;

    @Autowired
    UserServiceImplementation userService;

    @PostMapping("/user")
    public User createUser(@RequestBody User user){


        User savedUser = userService.registerUser(user);

        return savedUser;
    }

    @GetMapping("/api/users")
    public List<User> getUsers(){

        List<User> users = userRepo.findAll();

        return users;
    }

    @GetMapping("/api/users/{userID}")
    public User getUserByID(@PathVariable("userID") Integer id) throws Exception  {

        User user = userService.findUserById(id);
        return user;

    }



    @PutMapping("/api/user/{userId}")
    public User updateUser(@RequestBody User user,@PathVariable Integer userId) throws Exception{

       User users = userService.updateUser(user,userId);
        return users;
    }

//    @DeleteMapping("/user/{id}")
//    public String deleteUser(@PathVariable("id") Integer id) throws Exception{
//
//        Optional<User> user = userRepo.findById(id);
//
//        if(user.isEmpty()){
//            throw new Exception("User not exist inorder to delete");
//        }
//
//        userRepo.delete(user.get());
//        return "User has been deleted " + id;
//    }

//    @GetMapping("/user/{email}")
//    public User findByEmail(@PathVariable("email") String email){
//
//        User user = userService.findUserByEmail(email);
//
//        return user;
//    }

    @PutMapping("/api/user/follow/{userId1}/{userId2}")
    public User followUserHandler(@PathVariable Integer userId1,@PathVariable Integer userId2) throws Exception {

        User user = userService.followUser(userId1,userId2);

        return user;
    }


    @GetMapping("/api/user/search")
    public List<User> searchUser(@RequestParam("query") String query){

        List<User> user = userService.searchUser(query);

        return user;
    }

}
