package com.last.project.payload.request;

import com.last.project.entities.Role;
import lombok.Data;

import java.util.Set;

@Data

public class SignUpRequest {
    private String username;

    private String email;

    private Set<String> roles;

    private String password;
}
