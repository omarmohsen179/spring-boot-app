package com.example.demo.user_testing;

import com.example.demo.DemoApplication;
import com.example.demo.configuration.exception.Types.RequestException;
import com.example.demo.user.User;
import com.example.demo.user.UserRepository;
import com.example.demo.user.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
//@ExtendWith(SpringExtension.class)
//@SpringBootTest(classes = {DemoApplication.class})

//unit Testing
//@RunWith(MockitoJUnitRunner.class)
@SpringBootTest(classes = DemoApplication.class)
class UserServiceTest {
    // Create mock for DAO
    @Mock
    private UserRepository mockUserRepository;
    //	Inject mock into service
    @InjectMocks
    private UserService mockUserService;
    public User Builder(){
        String email = "user@test.com";
        return new User("user","userTest",email,"pass", LocalDate.of(2000,2,25),
                new HashSet<>(List.of())
        );
    }



    @Test
    public void canGetAllUsers()
    {
        mockUserService.GetAll();
        verify(mockUserRepository,times(1)).findAll();
    }

    @Test
    public void canAddUser() throws Exception {
        //when
        mockUserService.AddUser(Builder());
        //then
        ArgumentCaptor<User> userArgumentCaptor =
                ArgumentCaptor.forClass(User.class);
        verify(mockUserRepository).save(userArgumentCaptor.capture());
        assertThat(userArgumentCaptor.getValue()).isEqualTo(Builder());
    }
    @Test
    public void throwOnTakenEmail() {
        // edit on value from the Repository
        // here we edit on the function which check if email exist or not to return true
        // to throw exception
        // we used verify with parameter to check if we reached the insert statment or not
        given(mockUserRepository.existsByEmail(anyString()))
                .willReturn(true);
        assertThatThrownBy(() -> mockUserService.AddUser(Builder()))
                .isInstanceOf(RequestException.class)
                .hasMessageContaining("Email " + Builder().getEmail() + " taken");
        verify(mockUserRepository, never()).save(any());
    }
    @Test
    public void deleteUser(){
        given(mockUserRepository.existsById(1L)).willReturn(true);
        mockUserService.DeleteUser(1L);
        verify(mockUserRepository, times(1)).deleteById(any());
    }
    @Test
    public void throwDeleteUser(){
        assertThatThrownBy(()->mockUserService.DeleteUser(1L)).isInstanceOf(RequestException.class)
                .hasMessageContaining(  "user with id " +1L+ " does not exists");
        verify(mockUserRepository, never()).deleteById(any());
    }
    @Test
    public void TestService()
    {
        assertThat(mockUserService.TestService()).isEqualTo(200);
    }
}
