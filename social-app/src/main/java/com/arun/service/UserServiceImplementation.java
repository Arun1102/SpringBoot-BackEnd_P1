package com.arun.service;

import com.arun.Exception.UserException;
import com.arun.config.JwtProvider;
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

        throw new Exception("User not found from the userId: " + userId);
    }

    @Override
    public User findUserByEmail(String email) {

        User user = userRepo.findByEmail(email);



        return user;
    }

    @Override
    public User followUser(Integer reqUserId, Integer userId2) throws Exception {

        User reqUser = findUserById(reqUserId);
        User user2 = findUserById(userId2);

        user2.getFollowers().add(reqUser.getId());
        reqUser.getFollowings().add(user2.getId());

        userRepo.save(reqUser);
        userRepo.save(user2);


        return reqUser;
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

        if(user.getGender()!=null){
            oldUser.setGender(user.getGender());
        }

        User updatedUser = userRepo.save(oldUser);

        return updatedUser;
    }

    @Override
    public List<User> searchUser(String query) {

        return userRepo.searchUser(query);

    }

    @Override
    public User findUserByJWT(String jwt) {

        String email = JwtProvider.getEmailFromJwtToken(jwt);

        User user = userRepo.findByEmail(email);
        return user;
    }
}
