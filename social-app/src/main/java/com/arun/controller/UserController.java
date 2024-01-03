package com.arun.controller;

import com.arun.models.User;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {

    @GetMapping("/users")
    public List<User> getUsers(){

        List<User> users = new ArrayList<>();

        User user1 = new User(1,"Arun","Attappan","arun@gmail.com","1234");
        User user2 = new User(2,"Dewi","Gana","Dewi@gmail.com","1234");
        users.add(user1);
        users.add(user2);
        return users;
    }

    @GetMapping("/users/{userID}")
    public User getUserByID(@PathVariable("userID") Integer id){

        User user1 = new User(1,"Arun","Attappan","arun@gmail.com","1234");
        user1.setId(id);
        return user1;
    }

    @PostMapping("/user")
    public User createUser(@RequestBody User user){

        User newUser = new User();
        newUser.setEmail(user.getEmail());
        newUser.setFirstName(user.getFirstName());
        newUser.setLastName(user.getLastName());
        newUser.setPassword(user.getPassword());
        return newUser;
    }

    @PutMapping("/user")
    public User updateUser(@RequestBody User user){
        User user1 = new User(1,"Arun","Attappan","arun@gmail.com","1234");

        user1.setFirstName(user.getFirstName());

        return user1;
    }

    @DeleteMapping("/user/{id}")
    public String deleteUser(@PathVariable("id") Integer id){

        return "User deleted Succesfully " + id;
    }

}
