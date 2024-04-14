package com.last.project.registration;

import com.last.project.entities.Role;
import lombok.*;

import java.util.HashSet;
import java.util.Set;


@Data
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class RegistrationRequest {
    private final String firstName;
    private final String lastName;
    private final String email;
    private final String password;
//    private Set<Role> roles = new HashSet<>();
//
}
