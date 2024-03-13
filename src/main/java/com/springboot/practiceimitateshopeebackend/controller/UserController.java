package com.springboot.practiceimitateshopeebackend.controller;

import com.springboot.practiceimitateshopeebackend.model.LoginRequest;
import com.springboot.practiceimitateshopeebackend.model.LoginResponse;
import com.springboot.practiceimitateshopeebackend.model.UserModel;
import com.springboot.practiceimitateshopeebackend.service.UserService;
import com.springboot.practiceimitateshopeebackend.service.impl.UserServiceImpl;
import jakarta.validation.Valid;
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
    public UserModel register(@RequestBody @Valid UserModel userModel){
        return userService.register(userModel);
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public LoginResponse login(@RequestBody LoginRequest loginRequest){
        return userService.login(loginRequest);
    }
}
