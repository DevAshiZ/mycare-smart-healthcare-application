package com.csse.mycare.security.service;

import com.csse.mycare.admin.dto.BaseRegistrationRequest;
import com.csse.mycare.admin.dto.DoctorRegistrationRequest;
import com.csse.mycare.admin.dto.PharmacyRegistrationRequest;
import com.csse.mycare.common.constants.Role;
import com.csse.mycare.masterservice.dao.Doctor;
import com.csse.mycare.masterservice.dao.Patient;
import com.csse.mycare.masterservice.dao.Pharmacy;
import com.csse.mycare.masterservice.repository.DoctorRepository;
import com.csse.mycare.masterservice.repository.PatientRepository;
import com.csse.mycare.masterservice.repository.PharmacyRepository;
import com.csse.mycare.masterservice.repository.UserRepository;
import com.csse.mycare.security.dto.AuthenticationRequest;
import com.csse.mycare.security.dto.AuthenticationResponse;
import com.csse.mycare.security.dto.PatientRegisterRequest;
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

    private final UserRepository userRepository;
    private final PatientRepository patientRepository;
    private final PharmacyRepository pharmacyRepository;
    private final DoctorRepository doctorRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(PatientRegisterRequest patientRegisterRequest) {
        var patient = Patient.builder()
                .firstName(patientRegisterRequest.getFirstName())
                .lastName(patientRegisterRequest.getLastName())
                .email(patientRegisterRequest.getEmail())
                .password(passwordEncoder.encode(patientRegisterRequest.getPassword()))
                .age(Integer.valueOf(patientRegisterRequest.getAge()))
                .gender(patientRegisterRequest.getGender())
                .build();

        // Save user to database
        patientRepository.save(patient);

        // Generate JWT token for the user
        var jwtToken = jwtService.generateToken(patient);

        log.info("User registered successfully: {}", patient.getEmail());

        // Return the token
        return AuthenticationResponse.builder().token(jwtToken).build();
    }

    public Boolean registerWithRole(BaseRegistrationRequest registerRequest, Role role) {
        try {
            if (role == Role.DOCTOR) {
                DoctorRegistrationRequest doctorRequest = (DoctorRegistrationRequest) registerRequest;
                var doctor = Doctor.builder()
                        .firstName(doctorRequest.getFirstName())
                        .lastName(doctorRequest.getLastName())
                        .email(doctorRequest.getEmail())
                        .password(passwordEncoder.encode(doctorRequest.getPassword()))
                        .specialization(doctorRequest.getSpecialization())
                        .registrationNumber(doctorRequest.getRegistrationNumber())
                        .build();
                doctorRepository.save(doctor);
            } else if (role == Role.PHARMACY) {
                PharmacyRegistrationRequest pharmacyRegistrationRequest = (PharmacyRegistrationRequest) registerRequest;
                var pharmacy = Pharmacy.builder()
                        .firstName(pharmacyRegistrationRequest.getFirstName())
                        .lastName(pharmacyRegistrationRequest.getLastName())
                        .email(pharmacyRegistrationRequest.getEmail())
                        .password(passwordEncoder.encode(pharmacyRegistrationRequest.getPassword()))
                        .pharmacyName(pharmacyRegistrationRequest.getPharmacyName())
                        .pharmacyAddress(pharmacyRegistrationRequest.getPharmacyAddress())
                        .build();

                pharmacyRepository.save(pharmacy);
            } else if (role == Role.PATIENT) {
                // TODO: Move the patient registration here and separate token generation
            } else {
                log.error("Invalid role: {}", role);
                return false;
            }
        } catch (Exception e) {
            log.error("Error registering user: {}", e.getMessage());
            return false;
        }

        log.info("{} registered successfully: {}", role, registerRequest.getEmail());
        return true;
    }

    public AuthenticationResponse authenticate(AuthenticationRequest authRequest) {
        // Authenticate user
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    authRequest.getEmail(),
                    authRequest.getPassword()
            ));
        } catch (Exception e) {
            log.error("Authentication failed: {}", e.getMessage());
            throw new RuntimeException("Authentication failed");
        }

        // Get user details
        var user = userRepository.findByEmail(authRequest.getEmail())
                .orElseThrow(() -> {
                    log.error("User not found with email: {}", authRequest.getEmail());
                    return new RuntimeException("User not found");
                });

        // Generate JWT token
        var jwtToken = jwtService.generateToken(user);

        log.info("User authenticated successfully: {}", user.getEmail());

        // Return the token
        return AuthenticationResponse.builder().token(jwtToken).role(user.getRole().name()).build();
    }

}
