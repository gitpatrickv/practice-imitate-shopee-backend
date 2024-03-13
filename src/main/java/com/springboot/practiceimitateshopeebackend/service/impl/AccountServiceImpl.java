package com.springboot.practiceimitateshopeebackend.service.impl;

import com.springboot.practiceimitateshopeebackend.entity.User;
import com.springboot.practiceimitateshopeebackend.model.ChangePasswordRequest;
import com.springboot.practiceimitateshopeebackend.model.UpdateUserRequest;
import com.springboot.practiceimitateshopeebackend.repository.UserRepository;
import com.springboot.practiceimitateshopeebackend.service.AccountService;
import com.springboot.practiceimitateshopeebackend.utils.StringUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Slf4j
public class AccountServiceImpl implements AccountService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    @Override
    public void changePassword(ChangePasswordRequest request, Principal user) {

        User newPassword = (User) ((UsernamePasswordAuthenticationToken) user).getPrincipal();

        if(!passwordEncoder.matches(request.getOldPassword(), newPassword.getPassword())){
            throw new BadCredentialsException(StringUtils.WRONG_PASSWORD);
        }
        if(!request.getNewPassword().matches(request.getConfirmPassword())){
            throw new BadCredentialsException(StringUtils.PASSWORD_NOT_MATCH);
        }
        newPassword.setPassword(passwordEncoder.encode(request.getNewPassword()));
        userRepository.save(newPassword);

    }

    @Override
    public UpdateUserRequest updateUserInfo(UpdateUserRequest request) {

        User user = userRepository.findById(request.getEmail())
                .orElseThrow(() -> new NoSuchElementException(StringUtils.USER_NOT_FOUND));

        user.setName(request.getName() != null ? request.getName() : user.getName());

        user.setAddress(request.getAddress() != null ? request.getAddress() : user.getAddress());

        user.setContactNumber(request.getContactNumber() != null ? request.getContactNumber() : user.getContactNumber());

        userRepository.save(user);

        return UpdateUserRequest.builder()
                .name(user.getName())
                .address(user.getAddress())
                .contactNumber(user.getContactNumber())
                .build();
    }


}
