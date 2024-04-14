package com.last.project.services.servicesImp;

import com.last.project.dto.SignUpRequestDTO;
import com.last.project.dto.UserDto;
import com.last.project.entities.Role;
import com.last.project.entities.User;
import com.last.project.enums.ERole;
import com.last.project.enums.UserRole;
import com.last.project.payload.request.SignUpRequest;
import com.last.project.repositories.RoleRepository;
import com.last.project.repositories.UserRepository;
import com.last.project.services.authentification.AuthService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class AuthServiceImp implements AuthService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder; // Assuming passwordEncoder is injected
    public UserDto signupCreateur(SignUpRequestDTO signUpRequestDTO) {
//            User user = new User();
//            modelMapper.map(signUpRequestDTO, user);
//            user.setName(signUpRequestDTO.getName());
//            user.setEmail(signUpRequestDTO.getEmail());
//            //  user.setPassword(new BCryptPasswordEncoder().encode(signUpRequestDTO.getPassword()));
//            user.setPassword(signUpRequestDTO.getPassword());
//            Role createurRole = roleRepository.findByName(UserRole.CREATEUR)
//                    .orElseThrow(() -> new RuntimeException("Role CREATEUR non trouvé."));
//            user.setRole(createurRole);
//            User savedUser = userRepository.save(user);
//            return modelMapper.map(savedUser, UserDto.class);
        return  null;
        }
//
    public Boolean existParEmail(String email){
        return userRepository.findFirstByEmail(email) != null;
    }
//
//
    @Override
    public UserDto signupEntrepreneur(SignUpRequestDTO signUpRequestDTO ) {
//        User user = new User();
//        modelMapper.map(signUpRequestDTO, user); // Map properties from DTO to User
//
//        // Handle password encryption separately
////        user.setPassword(new BCryptPasswordEncoder().encode(signUpRequestDTO.getPassword()));
//        user.setName(signUpRequestDTO.getName());
//        user.setEmail(signUpRequestDTO.getEmail());
//
//
//        user.setPassword(signUpRequestDTO.getPassword());
//        Role entrepreneurRole = roleRepository.findByName(UserRole.ENTREPRENEUR)
//                .orElseThrow(() -> new RuntimeException("Role entrepreneur non trouvé."));
//        user.setRole(entrepreneurRole);
//        User savedUser = userRepository.save(user);
//        return modelMapper.map(savedUser, UserDto.class); // Map User to UserDto
        return  null;

    }

}
