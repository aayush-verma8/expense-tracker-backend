package com.aayush.expense_tracker.controller;
import com.aayush.expense_tracker.model.User;
import com.aayush.expense_tracker.service.JwtService;
import com.aayush.expense_tracker.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.*;
import com.aayush.expense_tracker.model.LoginRequest;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final UserService userService;
    private final JwtService jwtService;

    public AuthController(UserService userService, JwtService jwtService){
        this.userService=userService;
        this.jwtService = jwtService;
    }
    @PostMapping("/register")
    public ResponseEntity<User>register(@RequestBody User user){
        User savedUser=userService.register(user);
        return ResponseEntity.status(201).body(savedUser);
    }
    @PostMapping("/login")
    public ResponseEntity<?>login(@RequestBody LoginRequest request){
        User user=userService.login(request.getEmail(),request.getPassword());
        String token= jwtService.generateToken(user.getEmail());
        return ResponseEntity.ok().body(token);
    }
    @GetMapping("/test")
    public String test() {
        return "API is working 🚀";
    }
}
