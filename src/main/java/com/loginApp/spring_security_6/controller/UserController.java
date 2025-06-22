package com.loginApp.spring_security_6.controller;

import com.loginApp.spring_security_6.entity.User;
import com.loginApp.spring_security_6.repository.UserRepository;
import com.loginApp.spring_security_6.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
public class UserController {
    private final UserRepository userRepository;
    private final UserService userService;

    public UserController(UserRepository userRepository, UserService userService) {
        this.userRepository = userRepository;
        this.userService = userService;
    }

    @PostMapping("/register")
    public User register(@RequestBody User user){
//        return userRepository.save(user);
        return  userService.registerUser(user);
    }

    @PostMapping("/login")
    public String login(@RequestBody User user){
//        var u = userRepository.findByUserName(user.getUserName());
//        if(Objects.isNull(u)){
//            return "failure";
//        }
//        return "success";
        return userService.verify(user);
    }
}
