package com.springboot.practiceimitateshopeebackend.model;

import com.springboot.practiceimitateshopeebackend.entity.constants.Gender;
import com.springboot.practiceimitateshopeebackend.entity.constants.Role;
import com.springboot.practiceimitateshopeebackend.validation.UniqueEmailValid;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserModel{

    @Valid

    @UniqueEmailValid
    private String email;
    private String name;
    private String address;
    private String contactNumber;
    private String password;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    @Enumerated(EnumType.STRING)
    private Role role;
}
