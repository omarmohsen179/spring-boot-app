package com.example.demo.user;

import com.example.demo.role.Role;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
@Entity
@Table(name = "users",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "username"),
                @UniqueConstraint(columnNames = "email")
        })
@Getter
@Setter
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String username;
    @Size(max = 50)
    @Email
    private String email;
    private String password;
    private LocalDate date_of_birth;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> Roles = new HashSet<>();



    public User(Long id, String name, String username, String email, String password, LocalDate date_of_birth, Set<Role> roles) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.email = email;
        this.password = password;
        this.date_of_birth = date_of_birth;
        Roles = roles;
    }

    public User(String name, String username, String email, String password, LocalDate date_of_birth) {
        this.name = name;
        this.username = username;
        this.email = email;
        this.password = password;
        this.date_of_birth = date_of_birth;
    }

    public User(String name, String username, String email, String password, LocalDate date_of_birth, Set<Role> roles) {
        this.name = name;
        this.username = username;
        this.email = email;
        this.password = password;
        this.date_of_birth = date_of_birth;
        Roles = roles;
    }


}
