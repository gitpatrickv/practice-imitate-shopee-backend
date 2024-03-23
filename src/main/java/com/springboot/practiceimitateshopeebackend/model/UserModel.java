package com.springboot.practiceimitateshopeebackend.model;

import com.springboot.practiceimitateshopeebackend.entity.constants.Gender;
import com.springboot.practiceimitateshopeebackend.entity.constants.Role;
import com.springboot.practiceimitateshopeebackend.validation.ConfirmPasswordValid;
import com.springboot.practiceimitateshopeebackend.validation.UniqueEmailValid;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ConfirmPasswordValid
public class UserModel{

    @Valid

    @UniqueEmailValid
    @NotBlank(message = "{email.required}")
    private String email;
    @NotBlank(message = "{name.required}")
    private String name;
    @NotBlank(message = "{address.required}")
    private String address;
    @NotBlank(message = "{phone.number.required}")
    private String contactNumber;
    @NotBlank(message = "{password.required}")
    private String password;
    @NotBlank(message = "{confirm.password.required}")
    private String confirmPassword;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    @Enumerated(EnumType.STRING)
    private Role role;
}
