package com.example.demo.reflection;


import com.example.demo.DemoApplication;
import com.example.demo.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes ={DemoApplication.class})
public class Reflection {

    User user ;
    @BeforeEach
    void setUp(){
        user= User.builder().id(15L).name("Test").email("Email@gmail.com").build();


    }
    @Test
    void SetPrivateField(){
        ReflectionTestUtils.setField(user,"id",1L);
        assertEquals(1L,user.getId(),"Fail to access private variable");
    }
    @Test
    void GetPrivateField(){
        String email = (String)ReflectionTestUtils.getField(user,"email");
        assertEquals(user.getEmail(),email,"Fail to access private variable");
    }
    @Test
    void GetPrivateMethod(){
        String nameAndId = ReflectionTestUtils.invokeMethod(user,"GetNameAndId");
        assertEquals(user.getName()+" "+user.getId(),nameAndId,"Fail to access private method");

        System.out.println(user.getId());
    }
}
