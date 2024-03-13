package com.springboot.practiceimitateshopeebackend.config;


import com.springboot.practiceimitateshopeebackend.entity.User;
import com.springboot.practiceimitateshopeebackend.entity.constants.Role;
import com.springboot.practiceimitateshopeebackend.repository.UserRepository;
import com.springboot.practiceimitateshopeebackend.utils.StringUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.metamodel.internal.AbstractDynamicMapInstantiator;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class UserConfig {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Bean
    public CommandLineRunner commandLineRunner(){
        return args -> {

            if(!userRepository.existsByEmail("admin@gmail.com")) {
                User user = new User();
                user.setName("ADMIN");
                user.setEmail("admin@gmail.com");
                user.setPassword(passwordEncoder.encode("admin"));
                user.setRole(Role.valueOf("ADMIN"));
                userRepository.save(user);
                log.info(StringUtils.ADMIN_CREATED);
            }
            else{
                log.info(StringUtils.ADMIN_ALREADY_EXISTS);
            }
        };
    }
}
