package com.last.project.entities;

import com.last.project.dto.UserDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;
    private Boolean locked = false;
    private Boolean enabled = false;

//    @Column(name = "name")
    public Boolean isEnabled() {
        return this.enabled;
    }


    private String name;
//
//    private String lastname;
//    private String phone;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    // Constructor with specific parameters
    public User(String username, String email, String password, String name, String lastname, String phone) {
        this.username = username;
        this.email = email;
        this.password = password;
//        this.name = name;
//        this.lastname = lastname;
//        this.phone = phone;
        // Initialize roles as necessary
    }


    public UserDto getDto(){
            UserDto userDto = new UserDto();

      //      userDto.setName(name);
            userDto.setEmail(email);
//            userDto.setRole(role);
            return userDto;
        }
    public User(String username, String email, String password) {
        this.username = username;

        this.email = email;
        this.password = password;
        // Initialize other fields as necessary
    }


}
