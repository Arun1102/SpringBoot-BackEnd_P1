package com.arun.controller;

import com.arun.config.JwtProvider;
import com.arun.models.User;
import com.arun.repository.UserRepository;
import com.arun.request.LoginRequest;
import com.arun.response.AuthResponse;
import com.arun.service.CustomerUserDetailsSerice;
import com.arun.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepo;

    @Autowired
    private CustomerUserDetailsSerice customerUserDetails;

    @PostMapping("/signup")
    public AuthResponse createUser(@RequestBody User user) throws Exception {

        User isExist = userRepo.findByEmail(user.getEmail());

        if(isExist != null){
            throw new Exception("email already in use with another account");
        }


        User newUser = new User();

        newUser.setEmail(user.getEmail());
        newUser.setFirstName(user.getFirstName());
        newUser.setLastName(user.getLastName());
        newUser.setPassword(passwordEncoder.encode(user.getPassword()));

        User savedUser = userRepo.save(newUser);

        Authentication authentication = new UsernamePasswordAuthenticationToken(savedUser.getEmail(),savedUser.getPassword());

        String token = JwtProvider.generateToken(authentication);

        AuthResponse res = new AuthResponse(token,"register succesfull");
        return res;
    }

    @PostMapping("/signin")
    public AuthResponse signIn(@RequestBody LoginRequest loginRequest){

        Authentication authentication = authenticate(loginRequest.getEmail(),loginRequest.getPassword());

        String token = JwtProvider.generateToken(authentication);

        AuthResponse res = new AuthResponse(token,"Login succesfull");
        return res;

    }

    private Authentication authenticate(String email, String password) {
        UserDetails userDetails = customerUserDetails.loadUserByUsername(email);

        if(userDetails == null){
            throw new BadCredentialsException("invalid username");
        }

        if(!passwordEncoder.matches(password,userDetails.getPassword())){
            throw new BadCredentialsException("password not matched");
        }
        return new UsernamePasswordAuthenticationToken(userDetails,
                null,
                userDetails.getAuthorities());
    }


}
