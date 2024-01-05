package com.arun.service;

import com.arun.models.User;
import com.arun.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImplementation implements UserService{

    @Autowired
    UserRepository userRepo;

    @Override
    public User registerUser(User user) {

        User savedUser = userRepo.save(user);
        return savedUser;

    }

    @Override
    public User findUserById(Integer userId) throws Exception {
        Optional<User> user = userRepo.findById(userId);
        if(user.isPresent()){
            return user.get();
        }

        throw new Exception("Not found user");
    }

    @Override
    public User findUserByEmail(String email) {

        User user = userRepo.findByEmail(email);



        return user;
    }

    @Override
    public User followUser(Integer userId1, Integer userId2) throws Exception {

        User user1 = findUserById(userId1);
        User user2 = findUserById(userId2);

        user2.getFollowers().add(user1.getId());
        user1.getFollowings().add(user2.getId());

        userRepo.save(user1);
        userRepo.save(user2);


        return user1;
    }

    @Override
    public User updateUser(User user,Integer userId) throws Exception {
        Optional<User> users = userRepo.findById(userId);

        if(users.isEmpty()){
            throw new Exception("User not exist with id " + userId);
        }

        User oldUser = users.get();
        if(user.getFirstName()!=null){
            oldUser.setFirstName(user.getFirstName());
        }

        if(user.getLastName()!=null){
            oldUser.setLastName(user.getLastName());
        }

        if(user.getEmail()!=null){
            oldUser.setEmail(user.getEmail());
        }

        User updatedUser = userRepo.save(oldUser);

        return updatedUser;
    }

    @Override
    public List<User> searchUser(String query) {

        return userRepo.searchUser(query);

    }
}
