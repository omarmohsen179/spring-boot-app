package com.example.demo.configuration.profiles;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;


@Configuration
@Profile("dev")
//to make specific Configuration on app in case of dev profile active
public class devConfig {

}
