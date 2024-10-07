package com.csse.mycare.security.service;

import com.csse.mycare.masterservice.dao.Patient;
import com.csse.mycare.masterservice.repository.PatientRepository;
import com.csse.mycare.security.dto.AuthenticationRequest;
import com.csse.mycare.security.dto.AuthenticationResponse;
import com.csse.mycare.security.dto.RegisterRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final PatientRepository patientRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest registerRequest) {
        var patient = Patient.builder()
                .firstName(registerRequest.getFirstName())
                .lastName(registerRequest.getLastName())
                .email(registerRequest.getEmail())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .age(Integer.valueOf(registerRequest.getAge()))
                .gender(registerRequest.getGender())
                .build();

        // Save user to database
        patientRepository.save(patient);

        // Generate JWT token for the user
        var jwtToken = jwtService.generateToken(patient);

        log.info("User registered successfully: {}", patient.getEmail());

        // Return the token
        return AuthenticationResponse.builder().token(jwtToken).build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest authRequest) {
        // Authenticate user
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                authRequest.getEmail(),
                authRequest.getPassword()
        ));

        // Get user details
        var patient = patientRepository.findByEmail(authRequest.getEmail())
                .orElseThrow(() -> {
                    log.error("User not found with email: {}", authRequest.getEmail());
                    return new RuntimeException("User not found");
                });

        // Generate JWT token
        var jwtToken = jwtService.generateToken(patient);

        log.info("User authenticated successfully: {}", patient.getEmail());

        // Return the token
        return AuthenticationResponse.builder().token(jwtToken).role(patient.getRole().name()).build();
    }

}
