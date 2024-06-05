package com.mjlogin.mj.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mjlogin.mj.Service.UserService;
import com.mjlogin.mj.model.User;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200", methods = RequestMethod.POST, allowedHeaders = {"Content-Type"}, allowCredentials = "true" )
public class UserController {
    @Autowired
    private UserService userService;
    @GetMapping("/")
    public String Hello() {
    	return "Hello world";
    }

    @PostMapping("/register")
    public String register(@RequestBody User user) {
        if (userService.findByEmail(user.getEmail()).isPresent()) {
            return "Email already in use";
        }
        userService.registerUser(user);
        return "User registered successfully";
    }

    @PostMapping("/login")
    public String login(@RequestBody User user) {
        Optional<User> existingUser = userService.findByEmail(user.getEmail());
        if (existingUser.isPresent() && existingUser.get().getPassword().equals(user.getPassword())) {
            return "Login successful";
        } else {
            return "Invalid email or password";
        }
    }
}

