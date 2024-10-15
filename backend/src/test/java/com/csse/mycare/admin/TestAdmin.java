package com.csse.mycare.admin;

import com.csse.mycare.admin.dto.DoctorRegistrationRequest;
import com.csse.mycare.common.exceptions.UserAlreadyExistsException;
import com.csse.mycare.masterservice.MasterServiceImpl;
import com.csse.mycare.masterservice.dao.Doctor;
import com.csse.mycare.masterservice.dao.Pharmacy;
import com.csse.mycare.masterservice.service.DoctorService;
import com.csse.mycare.masterservice.service.PharmacyService;
import com.csse.mycare.security.service.AuthenticationService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@Slf4j
public class TestAdmin {
    @Mock
    DoctorService doctorService;

    @Mock
    PharmacyService pharmacyService;

    @Mock
    AuthenticationService authenticationService;

    @Mock
    PasswordEncoder passwordEncoder;

    @InjectMocks
    MasterServiceImpl masterService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetDoctorById() {
        Doctor mockDoctor = new Doctor(
                "ornithologist",
                "DOC002",
                null,
                null
        );

        mockDoctor.setUserId(1);
        when(doctorService.getDoctorById(1)).thenReturn(mockDoctor);
        Doctor doctor = masterService.getDoctorById(1);
        assertNotNull(doctor);
        log.info("Doctor is not null");
        assertEquals(1, doctor.getUserId());
        log.info("Doctor ID's match.");
    }

    @Test
    public void testGetPharmacyById() {
        Pharmacy mockPharmacy = new Pharmacy(
                "myPharmacy",
                "123, Galle Road, Colombo 03"
        );

        mockPharmacy.setUserId(1);
        when(pharmacyService.getPharmacyById(1)).thenReturn(mockPharmacy);
        Pharmacy pharmacy = pharmacyService.getPharmacyById(1);
        assertNotNull(pharmacy);
        log.info("Doctor is not null");
        assertEquals(1, pharmacy.getUserId());
        log.info("Doctor ID's match.");
    }

    @Test
    public void testInsertDoctor() {
        String email = "john.doe@john.doe.john.doe";
        String firstName = "John";
        String lastName = "Doe";
        String password = "password";
        String specialization = "ornithologist";
        String registrationNumber = "DOC002";
        try {
            DoctorRegistrationRequest mockDoctor = new DoctorRegistrationRequest(
                    firstName,
                    lastName,
                    email,
                    passwordEncoder.encode(password),
                    specialization,
                    registrationNumber
            );

            try {
                masterService.saveDoctor(mockDoctor);
            } catch (Exception e) {
                log.error("Error saving doctor: {}", e.getMessage());
            }

            Doctor savedDoctor = doctorService.getDoctorByEmail(email);
            assertNotNull(savedDoctor);
            assertEquals(email, savedDoctor.getEmail());
            assertEquals(firstName, savedDoctor.getFirstName());
            assertEquals(lastName, savedDoctor.getLastName());
            assertEquals(specialization, savedDoctor.getSpecialization());
            assertEquals(registrationNumber, savedDoctor.getRegistrationNumber());
            assertEquals(passwordEncoder.encode(password), savedDoctor.getPassword());
            log.info("Doctor is saved successfully.");
        } finally {
            if (doctorService.getDoctorByEmail(email) != null) {
                doctorService.deleteDoctor(doctorService.getDoctorByEmail(email).getUserId());
            }
        }
    }
}
