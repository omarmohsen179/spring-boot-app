package com.example.demo.user;

import com.example.demo.configuration.exception.Types.RequestException;
import com.example.demo.configuration.security.services.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserService  implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    @Transactional(readOnly = true)
    public List<User> GetAll(){
        return userRepository.findAll();
    }
    @Transactional(readOnly = true)
    public Optional<User> GetById(Long Id){
        return userRepository.findById(Id);
    }
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
    public User Add(User user){
        if(userRepository.findByUsername(user.getUsername()).isPresent()){
            throw new RequestException("username taken");
        }
        if(userRepository.findByUsername(user.getEmail()).isPresent()){
            throw new RequestException("email taken");
        }
        return userRepository.save(user);
    }
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
    public User Update(User user){
        if(userRepository.findById(user.getId()).isEmpty()){
            throw new RequestException("user not found");
        }
        return userRepository.save(user);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));
        return UserDetailsImpl.build(user);
    }

}
