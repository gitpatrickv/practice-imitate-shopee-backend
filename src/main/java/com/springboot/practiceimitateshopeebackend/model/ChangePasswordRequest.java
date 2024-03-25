package com.springboot.practiceimitateshopeebackend.model;

import com.springboot.practiceimitateshopeebackend.validation.PasswordMatchValid;
import com.springboot.practiceimitateshopeebackend.validation.PasswordValid;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@PasswordMatchValid
public class ChangePasswordRequest {
    @Valid

    @PasswordValid
    private String oldPassword;

    private String newPassword;

    private String confirmPassword;
}
