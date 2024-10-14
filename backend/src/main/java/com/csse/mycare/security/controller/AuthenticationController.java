package com.csse.mycare.security.controller;

import com.csse.mycare.security.dto.AuthenticationRequest;
import com.csse.mycare.security.dto.AuthenticationResponse;
import com.csse.mycare.security.dto.PatientRegisterRequest;
import com.csse.mycare.security.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@Slf4j
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<AuthenticationResponse> register(@RequestBody PatientRegisterRequest patientRegisterRequest) {
        log.info("Registering user: {}", patientRegisterRequest);
        return ResponseEntity.ok(authenticationService.register(patientRegisterRequest));
    }

    @PostMapping("/authenticate")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest authRequest) {
        log.info("Authenticating user: {}", authRequest);
        return ResponseEntity.ok(authenticationService.authenticate(authRequest));
    }

}
