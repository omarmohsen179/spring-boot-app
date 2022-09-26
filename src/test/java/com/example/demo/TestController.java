package com.example.demo;

import com.example.demo.user.User;
import com.example.demo.user.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Optional;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest
public class TestController {
    @MockBean
    private UserService userService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;
    @Test
    public void shouldReturnDefaultMessage() throws Exception {
        this.mockMvc.perform(get("/api/user/home")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Hello, World")));
    }
    @Test
    void shouldCreateUser() throws Exception {
        User tutorial = new User("dsd","name", "Spring Boot @WebMvcTest", "Description", LocalDate.of(2000,5,2));

        mockMvc.perform(post("/api/user").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(tutorial)))
                .andExpect(status().isCreated())
                .andDo(print());
    }

    @Test
    void shouldReturnUser() throws Exception {
        long id = 1L;
        User user =  new User("user","user","user@12.com","encoder.encode", LocalDate.of(2000,2,25),
                new HashSet<>()
        );

        mockMvc.perform(get("http://localhost:8080/api/user/{id}", id)).andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(user.getName()))
                .andExpect(jsonPath("$.username").value(user.getUsername()))
                .andDo(print());
    }

    @Test
    void shouldReturnNotFoundUser() throws Exception {
        long id = 1L;

        when(userService.GetById(id)).thenReturn(Optional.empty());
        mockMvc.perform(get("/api/user/{id}", id))
                .andExpect(status().isNotFound())
                .andDo(print());
    }


}
