package com.example.demo.user;

import com.example.demo.role.ERole;
import com.example.demo.role.Role;
import com.example.demo.role.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;

@Configuration
public class UserConfiguration {

    @Bean
    CommandLineRunner commandLineRunner(UserRepository repository, RoleRepository roleRepository){
        return args -> {
            Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN);
            repository.saveAll(List.of(
              new User("user","user","user@12.com","dfcc", LocalDate.of(2000,2,25),
                     new HashSet<>(List.of(adminRole))
                      )
            ));

        };

    }
}
