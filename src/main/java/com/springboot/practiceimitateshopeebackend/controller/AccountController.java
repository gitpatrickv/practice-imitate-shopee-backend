package com.springboot.practiceimitateshopeebackend.controller;

import com.springboot.practiceimitateshopeebackend.model.ChangePasswordRequest;
import com.springboot.practiceimitateshopeebackend.model.UpdateUserRequest;
import com.springboot.practiceimitateshopeebackend.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/account")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @PutMapping("/changePassword")
    @ResponseStatus(HttpStatus.OK)
    public void changePassword(@RequestBody ChangePasswordRequest request, Principal user) {
        accountService.changePassword(request,user);
    }
    @PutMapping("/updateInfo")
    @ResponseStatus(HttpStatus.OK)
    public UpdateUserRequest updateUserInfo(@RequestBody UpdateUserRequest request) {
        return accountService.updateUserInfo(request);
    }
}
