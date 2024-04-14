package com.last.project.controllers;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.last.project.dto.AuthenticationRequest;
import com.last.project.dto.SignUpRequestDTO;
import com.last.project.dto.UserDto;
import com.last.project.email.EmailSender;
import com.last.project.entities.Role;
import com.last.project.entities.User;
import com.last.project.enums.ERole;
import com.last.project.payload.request.LoginRequest;
import com.last.project.payload.request.SignUpRequest;
import com.last.project.payload.response.JwtResponse;
import com.last.project.payload.response.MessageResponse;
import com.last.project.registration.EmailValidator;
import com.last.project.registration.token.ConfirmationToken;
import com.last.project.registration.token.ConfirmationTokenRepository;
import com.last.project.registration.token.ConfirmationTokenService;
import com.last.project.repositories.RoleRepository;
import com.last.project.repositories.UserRepository;
import com.last.project.security.jwt.JwtUtils;
import com.last.project.security.services.UserDetailsImpl;
import com.last.project.services.AppUserService;
import com.last.project.services.authentification.AuthService;


import lombok.AllArgsConstructor;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@AllArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    AuthenticationManager authenticationManager;



    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;
    private final AppUserService appUserService;
    private final EmailValidator emailValidator;
    private final ConfirmationTokenService confirmationTokenService;
    private final ConfirmationTokenRepository confirmationTokenRepository;

    private final EmailSender emailSender;
    public static final String TOKEN_PREFIX ="Bearer ";
    public static final String HEADER_STRING ="Authorization";



    @PostMapping("/createur/signUp")
    public ResponseEntity<?>signupCreateur(@RequestBody SignUpRequestDTO signUpRequestDTO){
        if(authService.existParEmail(signUpRequestDTO.getEmail())){
            return new ResponseEntity<>("Createur deja exist avec cet email", HttpStatus.NOT_ACCEPTABLE);
        }
        UserDto createdUser = authService.signupCreateur(signUpRequestDTO);
        return new ResponseEntity<>(createdUser, HttpStatus.OK);
    }

    @PostMapping("/entrepreneur/signUp")
    public ResponseEntity<?>signupEntrepreneur(@RequestBody SignUpRequestDTO signUpRequestDTO){
        if(authService.existParEmail(signUpRequestDTO.getEmail())){
            return new ResponseEntity<>("entrepreneur deja exist avec cet email", HttpStatus.NOT_ACCEPTABLE);
        }
        UserDto createdUser = authService.signupEntrepreneur(signUpRequestDTO);
        return new ResponseEntity<>(createdUser, HttpStatus.OK);
    }
    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles));
    }
    @PostMapping("/login")
    public ResponseEntity<?> authenticate( @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        // Vérification de l'activation du compte utilisateur
        if (!userDetails.isEnabled()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Votre compte n'est pas activé. Veuillez vérifier votre email pour activer votre compte.");
        }

        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles));
    }
//    @PostMapping("/signup")
//    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
//        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
//            return ResponseEntity
//                    .badRequest()
//                    .body(new MessageResponse("Error: Username is already taken!"));
//        }
//
//        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
//            return ResponseEntity
//                    .badRequest()
//                    .body(new MessageResponse("Error: Email is already in use!"));
//        }
//
//        // Create new user's account
//        User user = new User(signUpRequest.getUsername(),
//                signUpRequest.getEmail(),
//                encoder.encode(signUpRequest.getPassword()));
//
//        Set<String> strRoles = signUpRequest.getRole();
//        Set<Role> roles = new HashSet<>();
//
//        if (strRoles == null) {
//            Role userRole = roleRepository.findByName(ERole.ROLE_ENTREPRENEUR)
//                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
//            roles.add(userRole);
//        } else {
//            strRoles.forEach(role -> {
//                switch (role) {
//                    case "ROLE_CREATEUR":
//                        Role adminRole = roleRepository.findByName(ERole.ROLE_CREATEUR)
//                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
//                        roles.add(adminRole);
//
//                        break;
//                    default:
//                        Role userRole = roleRepository.findByName(ERole.ROLE_ENTREPRENEUR)
//                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
//                        roles.add(userRole);
//                }
//            });
//        }
//
//        user.setRoles(roles);
//        userRepository.save(user);
//
//        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
//    }
//@PostMapping("/signup")
//public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
//    // Vérifie si le nom d'utilisateur est déjà pris
//    if (userRepository.existsByUsername(signUpRequest.getUsername())) {
//        return ResponseEntity
//                .badRequest()
//                .body(new MessageResponse("Error: Username is already taken!"));
//    }
//
//    // Vérifie si l'e-mail est déjà utilisé
//    if (userRepository.existsByEmail(signUpRequest.getEmail())) {
//        return ResponseEntity
//                .badRequest()
//                .body(new MessageResponse("Error: Email is already in use!"));
//    }
//    System.out.println("Password before encoding: " + signUpRequest.getPassword());
//    String encodedPassword = encoder.encode(signUpRequest.getPassword());
//    System.out.println("Password after encoding: " + encodedPassword);
//    // Crée un nouvel utilisateur
//    User user = new User(signUpRequest.getUsername(),
//            signUpRequest.getEmail(),
//            encodedPassword);
//
//
//    // Définit les rôles de l'utilisateur
//    Set<String> strRoles = signUpRequest.getRole();
//    Set<Role> roles = new HashSet<>();
//
//    // Gestion des rôles
//    if (strRoles == null) {
//        Role userRole = roleRepository.findByName(ERole.ROLE_ENTREPRENEUR)
//                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
//        roles.add(userRole);
//    } else {
//        strRoles.forEach(role -> {
//            switch (role) {
//                case "ROLE_CREATEUR":
//                    Role adminRole = roleRepository.findByName(ERole.ROLE_CREATEUR)
//                            .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
//                    roles.add(adminRole);
//                    break;
//                default:
//                    Role userRole = roleRepository.findByName(ERole.ROLE_ENTREPRENEUR)
//                            .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
//                    roles.add(userRole);
//            }
//        });
//    }
//
//    user.setRoles(roles);
//
//    // Enregistre l'utilisateur dans la base de données
//    userRepository.save(user);
//
//    // Génère un token de confirmation d'e-mail unique
//    String token = UUID.randomUUID().toString();
//
//    // Crée un nouvel objet ConfirmationToken et l'associe à l'utilisateur
//    ConfirmationToken confirmationToken = new ConfirmationToken(token, user);
//
//    // Sauvegarde le token dans la base de données
//    confirmationTokenRepository.save(confirmationToken);
//
//    // Envoie un e-mail de confirmation à l'utilisateur
//    String confirmationLink = "http://localhost:8080/api/v1/confirm-account?token=" + token;
//    emailSender.send(user.getEmail(), "Confirmation de votre compte", "Veuillez cliquer sur le lien suivant pour confirmer votre compte : " + confirmationLink);
//
//    return ResponseEntity.ok(new MessageResponse("User registered successfully! Check your email for confirmation."));
//}

}
