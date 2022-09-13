package com.example.demo.user;


import com.example.demo.configuration.exception.Types.NoDataException;
import com.example.demo.configuration.exception.Types.RequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("api/user")
public class UserController {
    @Autowired
    private UserService userService;


    @GetMapping("exception-no-data")
    public ResponseEntity<?> exceptionNodata(){
        throw new NoDataException();
    }
    @GetMapping("exception")
    public ResponseEntity<?> exception(){
         throw new RequestException("new error");
    }

    @GetMapping("custom-internal-error")
    public ResponseEntity<?> CustomInternalError() throws Exception {
        throw new IllegalArgumentException("test");
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
    public ResponseEntity<User> Insert(@RequestBody User user) {
        return  new ResponseEntity<>( userService.Save(user), HttpStatus.CREATED);
    }
}
