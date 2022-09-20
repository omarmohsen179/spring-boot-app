package com.example.demo.Mock;

import com.example.demo.DemoApplication;
import com.example.demo.user.User;
import com.example.demo.user.UserRepository;
import com.example.demo.user.UserService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

@SpringBootTest(classes = {DemoApplication.class})
public class MockTest {

    @Autowired
    ApplicationContext context;
    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private UserService userService;
    @BeforeEach
    public void setup(){
        userRepository= context.getBean("applicationDao",UserRepository.class);
        System.out.println(userRepository.findAll());
    }
    @Test
    public void Testx(){
        System.out.println(userRepository.findAll());
    }
}
