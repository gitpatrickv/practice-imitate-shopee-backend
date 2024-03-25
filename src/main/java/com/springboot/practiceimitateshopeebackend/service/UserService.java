package com.springboot.practiceimitateshopeebackend.service;

import com.springboot.practiceimitateshopeebackend.model.LoginRequest;
import com.springboot.practiceimitateshopeebackend.model.LoginResponse;
import com.springboot.practiceimitateshopeebackend.model.UserModel;

public interface UserService {

    UserModel register(UserModel userModel);
    LoginResponse login(LoginRequest loginRequest);

}
