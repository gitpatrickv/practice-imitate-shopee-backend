package com.springboot.practiceimitateshopeebackend.config;

import com.springboot.practiceimitateshopeebackend.security.JwtAuthenticationFilter;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static com.springboot.practiceimitateshopeebackend.entity.constants.Role.ADMIN;
import static com.springboot.practiceimitateshopeebackend.entity.constants.Role.SELLER;

@EnableWebSecurity
@EnableMethodSecurity
@Configuration
@AllArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorize ->
                    authorize
                            .requestMatchers(HttpMethod.POST, "/api/product/save").hasAnyAuthority(ADMIN.name(), SELLER.name())
                            .requestMatchers(HttpMethod.DELETE, "/api/product/delete/**").hasAnyAuthority(ADMIN.name(), SELLER.name())
                            .requestMatchers("/api/cart/**").authenticated()
                            .requestMatchers("/api/account/**").authenticated()
                            .requestMatchers("/api/order/**").authenticated()
                            .requestMatchers("/api/transactions/**").authenticated()
                            .requestMatchers("/api/product/**").permitAll()
                            .requestMatchers("/api/user/**").permitAll()
                            .requestMatchers("/api/variation/**").authenticated()
                            .anyRequest().authenticated()
                );
        httpSecurity.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        httpSecurity.authenticationProvider(authenticationProvider);
        httpSecurity.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        return httpSecurity.build();
    }

}
