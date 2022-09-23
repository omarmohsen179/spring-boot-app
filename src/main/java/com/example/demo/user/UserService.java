package com.example.demo.user;

import com.example.demo.configuration.exception.Types.RequestException;
import com.example.demo.configuration.security.services.UserDetailsImpl;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor
public class UserService  implements UserDetailsService {

    @Autowired
    private  UserRepository userRepository;

    @Transactional(readOnly = true)
    public int TestService(){
        return 200;
    }
    @Transactional(readOnly = true)
    public List<User> GetAll(){
        return userRepository.findAll();
    }
    @Transactional(readOnly = true)
    public Optional<User> GetById(Long Id){
        return userRepository.findById(Id);
    }
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
    public User AddUser(User user) throws RequestException {
        if(userRepository.existsByEmail(user.getEmail()))
            throw new RequestException("Email " + user.getEmail() + " taken");
        if(userRepository.existsByUsername(user.getUsername()))
            throw new RequestException("Username " + user.getEmail() + " taken");
        return userRepository.save(user);
    }
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
    public User UpdateUser(User user) throws RequestException {
        // check if another user take this mail or username or not except not
        if(userRepository.existsByEmailAndIdNot(user.getEmail(),user.getId()))
            throw new RequestException("Email " + user.getEmail() + " taken");
        if( userRepository.existsByEmailAndIdNot(user.getEmail(),user.getId()))
            throw new RequestException("Username " + user.getEmail() + " taken");
        return userRepository.save(user);
    }
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
    public void DeleteUser(Long Id) throws RequestException {
        if(!userRepository.existsById(Id)) {
            throw new RequestException(
                    "user with id " + Id + " does not exists");
        }
        userRepository.deleteById(Id);
    }
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));
        return UserDetailsImpl.build(user);
    }

}
