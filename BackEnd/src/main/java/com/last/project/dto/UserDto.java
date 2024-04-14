package com.last.project.dto;

import com.last.project.entities.Role;
import com.last.project.enums.ERole;
import com.last.project.enums.UserRole;
import lombok.Data;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
public class UserDto {
    private Long id;
    private String email;
    private String password;
    private String name;
    private String username;

    private String lastname;
    private String phone;
    private Role role;
    //private Set<Role> roles = new HashSet<>();
    //private List<ERole> role;
    //private UserRole role1;
}
