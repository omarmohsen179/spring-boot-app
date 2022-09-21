package com.example.demo.mock;

import com.example.demo.DemoApplication;
import com.example.demo.user.UserRepository;
import com.example.demo.user.UserService;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import static org.mockito.Mockito.verify;

@SpringBootTest(classes = {DemoApplication.class})
public class MockTest {

    @Autowired
    ApplicationContext context;
    @Mock
    private UserRepository mockUserRepository;
    @InjectMocks
    private UserService mockUserService;
    @Test
    public void canGetAllUsers()
    {
        mockUserService.GetAll();
        verify(mockUserRepository).findAll();
    }
}
