package com.example.demo.MVC;


import com.example.demo.user.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import net.minidev.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class MVCtest {
    @Autowired
        private MockMvc mockMvc;
    public User Builder(){
        String email = "user@test.com";
        return new User("user","userTest",email,"pass", LocalDate.of(2000,2,25),
                new HashSet<>(List.of())
        );
    }

    @Test
    void Test() throws Exception {
        this.mockMvc.perform(get("/api/user/home"))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello, World"));
    }
    @Test
    void testAddUser() throws Exception {
        this.mockMvc.perform(post("/api/user").contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(asJsonString(Builder())))
                        .andExpect(status().isCreated());
    }
    @Test
    void testEditUser() throws Exception {
        this.mockMvc.perform(post("/api/user").contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(asJsonString(Builder())))
                .andExpect(status().isCreated());
    }
    public static String asJsonString( Object obj) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        System.out.println(mapper.writeValueAsString(obj));
        return mapper.writeValueAsString(obj);

    }
}
