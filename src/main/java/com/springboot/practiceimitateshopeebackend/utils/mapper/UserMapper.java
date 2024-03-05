package com.springboot.practiceimitateshopeebackend.utils.mapper;

import com.springboot.practiceimitateshopeebackend.entity.User;
import com.springboot.practiceimitateshopeebackend.model.UserModel;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class UserMapper {

    private final ModelMapper mapper = new ModelMapper();

    public UserModel mapUserEntityToUserModel(User user){
        return mapper.map(user, UserModel.class);
    }

    public User mapUserModelToUserEntity(UserModel userModel){
        return mapper.map(userModel, User.class);
    }
}
