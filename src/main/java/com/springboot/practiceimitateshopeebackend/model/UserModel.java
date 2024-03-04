package com.springboot.practiceimitateshopeebackend.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserModel{
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String address;
    private String contactNumber;
}
