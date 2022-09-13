package com.example.demo;
import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.ArgumentMatchers.any;
import com.example.demo.user.User;
import com.example.demo.user.UserController;
import com.example.demo.user.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.time.LocalDate;
import java.util.*;

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
    void shouldCreateTutorial() throws Exception {
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
    void shouldReturnNotFoundTutorial() throws Exception {
        long id = 1L;

        when(userService.GetById(id)).thenReturn(Optional.empty());
        mockMvc.perform(get("/api/user/{id}", id))
                .andExpect(status().isNotFound())
                .andDo(print());
    }

   /*  @Test
   void shouldReturnListOfTutorials() throws Exception {
        List<User> tutorials = new ArrayList<>(
                Arrays.asList(new User(1L,"name", "Spring Boot @WebMvcTest", "Description", LocalDate.of(2000,5,2)),
                        new User(1L,"name", "Spring Boot @WebMvcTest", "Description", LocalDate.of(2000,5,2)),
                        new User(1L,"name", "Spring Boot @WebMvcTest", "Description", LocalDate.of(2000,5,2))));

        when(userService.findAll()).thenReturn(tutorials);
        mockMvc.perform(get("/api/tutorials"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(tutorials.size()))
                .andDo(print());
    }

    @Test
    void shouldReturnListOfTutorialsWithFilter() throws Exception {
        List<User> tutorials = new ArrayList<>(
                Arrays.asList(new User(1L,"name", "Spring Boot @WebMvcTest", "Description", LocalDate.of(2000,5,2)),
                        new User(1L,"name", "Spring Boot @WebMvcTest", "Description", LocalDate.of(2000,5,2))

                ));

        String title = "Boot";
        MultiValueMap<String, String> paramsMap = new LinkedMultiValueMap<>();
        paramsMap.add("title", title);

        when(userService.findByTitleContaining(title)).thenReturn(tutorials);
        mockMvc.perform(get("/api/tutorials").params(paramsMap))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(tutorials.size()))
                .andDo(print());
    }

    @Test
    void shouldReturnNoContentWhenFilter() throws Exception {
        String title = "BezKoder";
        MultiValueMap<String, String> paramsMap = new LinkedMultiValueMap<>();
        paramsMap.add("title", title);

        List<User> tutorials = Collections.emptyList();

        when(userService.findByTitleContaining(title)).thenReturn(tutorials);
        mockMvc.perform(get("/api/tutorials").params(paramsMap))
                .andExpect(status().isNoContent())
                .andDo(print());
    }

    @Test
    void shouldUpdateTutorial() throws Exception {
        long id = 1L;

        User tutorial = new User(id,"name", "Spring Boot @WebMvcTest", "Description", LocalDate.of(2000,5,2));
        User updatedtutorial = new User(id, "name","Updated", "Updated", true);

        when(userService.findById(id)).thenReturn(Optional.of(tutorial));
        when(userService.save(any(User.class))).thenReturn(updatedtutorial);

        mockMvc.perform(put("/api/tutorials/{id}", id).contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedtutorial)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value(updatedtutorial.getTitle()))
                .andExpect(jsonPath("$.description").value(updatedtutorial.getDescription()))
                .andExpect(jsonPath("$.published").value(updatedtutorial.isPublished()))
                .andDo(print());
    }

    @Test
    void shouldReturnNotFoundUpdateTutorial() throws Exception {
        long id = 1L;

        User updatedtutorial = new User(1L,"name", "Spring Boot @WebMvcTest", "Description", LocalDate.of(2000,5,2));

        when(userService.findById(id)).thenReturn(Optional.empty());
        when(userService.save(any(User.class))).thenReturn(updatedtutorial);

        mockMvc.perform(put("/api/tutorials/{id}", id).contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedtutorial)))
                .andExpect(status().isNotFound())
                .andDo(print());
    }

    @Test
    void shouldDeleteTutorial() throws Exception {
        long id = 1L;

        doNothing().when(userService).deleteById(id);
        mockMvc.perform(delete("/api/tutorials/{id}", id))
                .andExpect(status().isNoContent())
                .andDo(print());
    }

    @Test
    void shouldDeleteAllTutorials() throws Exception {
        doNothing().when(userService).deleteAll();
        mockMvc.perform(delete("/api/tutorials"))
                .andExpect(status().isNoContent())
                .andDo(print());
    }*/
}
