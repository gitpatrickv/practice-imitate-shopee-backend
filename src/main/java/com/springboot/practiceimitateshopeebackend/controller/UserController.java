package com.springboot.practiceimitateshopeebackend.controller;

import com.springboot.practiceimitateshopeebackend.model.UserModel;
import com.springboot.practiceimitateshopeebackend.service.UserService;
import com.springboot.practiceimitateshopeebackend.service.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    @PostMapping("/register")
    @ResponseStatus(HttpStatus.OK)
    public UserModel register(@RequestBody UserModel userModel){
        return userService.register(userModel);
    }
}
