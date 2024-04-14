package com.last.project.registration;

import com.last.project.entities.User;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/v1/registration")
@AllArgsConstructor
public class RegistrationController {

    private final RegistrationService registrationService;

    @PostMapping(path = "/creator")
    public String register(@RequestBody RegistrationRequest request) {
        return registrationService.register(request);
    }
    @PostMapping(path = "/entrepreneur")
    public String registerEntrepreneur(@RequestBody RegistrationRequest request) {
        return registrationService.registerEntrepreneur(request);
    }


    @GetMapping(path = "confirm")
    public String confirm(@RequestParam("token") String token) {
        return registrationService.confirmToken(token);
    }

}
