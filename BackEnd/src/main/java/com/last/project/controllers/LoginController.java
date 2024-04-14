package com.last.project.controllers;


import com.last.project.entities.User;
import com.last.project.payload.request.LoginRequest;

import com.last.project.payload.response.JwtResponse;
import com.last.project.repositories.RoleRepository;
import com.last.project.repositories.UserRepository;
import com.last.project.security.jwt.JwtUtils;
import com.last.project.security.services.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequestMapping("/api/vi")
@RestController

public class LoginController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;


@PostMapping("/login")
public ResponseEntity<?> authenticate(@RequestBody LoginRequest loginRequest) {

    Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

    SecurityContextHolder.getContext().setAuthentication(authentication);
    String jwt = jwtUtils.generateJwtToken(authentication);

    UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

    // Récupérer l'utilisateur depuis la base de données
  //  Optional<User> userOptional = userRepository.findByUsername(userDetails.getUsername());
    Optional<User> userOptional = userRepository.findByEmail(userDetails.getEmail());


    // Vérifier si l'utilisateur existe et s'il est activé
    if (userOptional.isPresent()) {
        User user = userOptional.get();
        if (user.isEnabled()) {
            List<String> roles = userDetails.getAuthorities().stream()
                    .map(item -> item.getAuthority())
                    .collect(Collectors.toList());

            return ResponseEntity.ok(new JwtResponse(jwt,
                    userDetails.getId(),
                    userDetails.getUsername(),
                    userDetails.getEmail(),
                    roles));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Votre compte n'est pas activé. Veuillez vérifier votre email pour activer votre compte.");
        }
    } else {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body("Utilisateur non trouvé.");
    }
}





}
