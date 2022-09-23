package com.example.demo.role;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class RoleConfiguration {

    @Bean
    CommandLineRunner commandLineRunnerRole(RoleRepository repository){
        return args -> {
            repository.saveAll(List.of(
              new Role(1L,ERole.ROLE_USER),new Role(2L,ERole.ROLE_ADMIN)
            ));

        };

    }
}
