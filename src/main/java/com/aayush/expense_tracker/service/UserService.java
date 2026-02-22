package com.aayush.expense_tracker.service;

import com.aayush.expense_tracker.respostiory.UserRespostiory;
import com.aayush.expense_tracker.model.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.authentication.BadCredentialsException;



@Service

public class UserService {
    private final UserRespostiory userRespostiory;
    private final PasswordEncoder passwordEncoder;
    public UserService(UserRespostiory userRespostiory,PasswordEncoder passwordEncoder){
        this.userRespostiory=userRespostiory;
        this.passwordEncoder=passwordEncoder;
    }
    public User register(User user){
        if(userRespostiory.existsByEmail(user.getEmail())){
            throw new RuntimeException("Email already registered");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRespostiory.save(user);
    }
    public User login(String email,String password){
        User user=userRespostiory.findByEmail(email).orElseThrow(()->new RuntimeException("Invalid email or password"));

        if(!passwordEncoder.matches(password, user.getPassword())){
            throw new RuntimeException("Invalid email pr password");
        }
        return user;
    }
}
