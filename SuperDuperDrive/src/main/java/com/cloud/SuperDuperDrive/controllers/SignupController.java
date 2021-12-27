package com.cloud.SuperDuperDrive.controllers;

import com.cloud.SuperDuperDrive.Mapper.UserMapper;
import com.cloud.SuperDuperDrive.model.User;
import com.cloud.SuperDuperDrive.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/signup")
public class SignupController {

    @Autowired
    private UserService userService;

    public SignupController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String SignupView(){
        return "signup";
    }

    @PostMapping
    public User addUser(User user){
        userService.createUser(user);
        System.out.println(userService.getUser(user.getUsername()).getUsername());

        return userService.getUser(user.getUsername());
    }
}
