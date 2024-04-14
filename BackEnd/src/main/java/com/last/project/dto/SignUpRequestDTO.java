package com.last.project.dto;

import com.last.project.enums.UserRole;
import lombok.Data;

@Data
public class SignUpRequestDTO {

    private Long id;
    private String email;
    private String phone;

    private String password;
    private String name;
    private String lastname;
}
