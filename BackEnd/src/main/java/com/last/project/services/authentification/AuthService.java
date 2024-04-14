package com.last.project.services.authentification;

import com.last.project.dto.SignUpRequestDTO;
import com.last.project.dto.UserDto;
import com.last.project.payload.request.SignUpRequest;

public interface AuthService {
   // UserDto signupCreateur(SignUpRequestDTO signUpRequestDTO);
    Boolean existParEmail(String email);
    //UserDto signupEntrepreneur(SignUpRequestDTO signUpRequestDTO);
     UserDto signupEntrepreneur(SignUpRequestDTO signUpRequest) ;
     UserDto signupCreateur(SignUpRequestDTO signUpRequestDTO);

}
