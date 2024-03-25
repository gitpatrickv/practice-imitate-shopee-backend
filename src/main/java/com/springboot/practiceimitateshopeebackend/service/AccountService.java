package com.springboot.practiceimitateshopeebackend.service;

import com.springboot.practiceimitateshopeebackend.model.ChangePasswordRequest;
import com.springboot.practiceimitateshopeebackend.model.UpdateUserRequest;

import java.security.Principal;

public interface AccountService {

    void changePassword(ChangePasswordRequest request, Principal user);

    UpdateUserRequest updateUserInfo(UpdateUserRequest request);
}
