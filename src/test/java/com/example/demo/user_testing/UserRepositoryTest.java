package com.example.demo.user_testing;

import com.example.demo.user.User;
import com.example.demo.user.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;

import static org.mockito.Mockito.verify;
//integration Testing
@DataJpaTest

public class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;
    public User Builder(){
        String email = "user@test.com";
        return new User("user","userTest",email,"pass", LocalDate.of(2000,2,25),
                new HashSet<>(List.of())
        );
    }

    @Test
    void UserByIterable()
    {
        userRepository.save(Builder());
        Iterable<User> users = userRepository.findAll();
        assertTrue(users.iterator().hasNext(),"We got users on database");
    }

    @Test
    void AddTest()
    {
        userRepository.save(Builder());
        assertThat(userRepository.existsByEmail(Builder().getEmail())).isTrue();
    }

}
