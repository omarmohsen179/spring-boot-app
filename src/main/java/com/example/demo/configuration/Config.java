package com.example.demo.configuration;

import com.example.demo.user.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {
    @Bean(name = "applicationDao")
    public UserService ApplicationDao() {
        return new UserService();
    }

}
