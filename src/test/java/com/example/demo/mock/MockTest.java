package com.example.demo.mock;

import com.example.demo.DemoApplication;
import com.example.demo.configuration.test.DemoUtils;
import com.example.demo.user.User;
import com.example.demo.user.UserRepository;
import com.example.demo.user.UserService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = {DemoApplication.class})
public class MockTest {

    @Autowired
    ApplicationContext context;
    @Mock
    private UserRepository mockUserRepository;
    @InjectMocks
    private UserService mockUserService;

    @MockBean(name = "applicationDao")
    private DemoUtils applicationDaoX;
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockUserService = new UserService(mockUserRepository);
    }
    @Test
    public void canGetAllUsers()
    {
        mockUserService.GetAll();
        verify(mockUserRepository,times(1)).findAll();
    }
    @Test
    void throw_run_time() {
        Object nullUser = context.getBean("user");
        //check if checkNull function throw runtime Error
        //with mock reposatory
        doThrow(new RuntimeException()).when(applicationDaoX).checkNull(nullUser);
        assertThrows(RuntimeException.class,()->{
            applicationDaoX.checkNull(nullUser);
        });
    }

}
