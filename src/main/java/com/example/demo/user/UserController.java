package com.example.demo.user;


import com.example.demo.configuration.exception.Types.BadRequestException;
import com.example.demo.configuration.exception.Types.NoDataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("api/user")
public class UserController {

    private final UserService userService;

    @Value("${spring.message}")
    private String message;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("exception-no-data")
    public ResponseEntity<?> exceptionNodata(){
        throw new NoDataException();
    }
    @GetMapping("exception")
    public ResponseEntity<?> exception(){
         throw new BadRequestException("new error");
    }
    @GetMapping("custom-internal-error")
    public ResponseEntity<?> CustomInternalError() throws Exception {
        throw new IllegalArgumentException("test");
    }
    @GetMapping("profile")

    public String profile(){
        return message;
    }
    @GetMapping("home")
    public ResponseEntity<?> home(){
        return ResponseEntity.ok(    "Hello, World");
    }
    @GetMapping
    public ResponseEntity<?> GetAll(){
        return ResponseEntity.ok(userService.GetAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getTutorialById(@PathVariable("id") long id) {
        Optional<User> user = userService.GetById(id);
        return user.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }
    @PostMapping
    public ResponseEntity<User> Insert(@RequestBody User user) throws Exception {
        return  new ResponseEntity<>( userService.AddUser(user), HttpStatus.CREATED);
    }
}
