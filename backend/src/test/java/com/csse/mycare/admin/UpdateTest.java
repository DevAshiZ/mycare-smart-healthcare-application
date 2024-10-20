package com.csse.mycare.admin;

import com.csse.mycare.admin.controller.DoctorController;
import com.csse.mycare.admin.controller.PharmacyController;
import com.csse.mycare.admin.controller.ScheduleController;
import com.csse.mycare.admin.dto.DoctorRegistrationRequest;
import com.csse.mycare.admin.dto.PharmacyRegistrationRequest;
import com.csse.mycare.common.constants.Role;
import com.csse.mycare.masterservice.MasterService;
import com.csse.mycare.masterservice.dao.Doctor;
import com.csse.mycare.masterservice.dao.Pharmacy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class UpdateTest {

    private MockMvc mockMvc;

    @Mock
    private MasterService masterService;

    @InjectMocks
    private DoctorController doctorController;

    @InjectMocks
    private PharmacyController pharmacyController;

    @InjectMocks
    private ScheduleController scheduleController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(doctorController, pharmacyController, scheduleController).build();
    }

    @Test
    public void testUpdateDoctor_Success() throws Exception {
        DoctorRegistrationRequest request = new DoctorRegistrationRequest();
        request.setEmail("test@example.com");

        Doctor doctor = new Doctor();
        doctor.setRole(Role.DOCTOR);
        doctor.setEmail("test@example.com");

        when(masterService.updateDoctor(any(DoctorRegistrationRequest.class))).thenReturn(doctor);

        mockMvc.perform(post("/admin/doctor/update-doctor")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"email\":\"test@example.com\"}"))
                .andExpect(status().isOk());
    }

    @Test
    public void testUpdatePharmacy_Success() throws Exception {
        PharmacyRegistrationRequest request = new PharmacyRegistrationRequest();
        request.setEmail("test@example.com");

        Pharmacy pharmacy = new Pharmacy();
        pharmacy.setRole(Role.PHARMACY);
        pharmacy.setEmail("test@example.com");

        when(masterService.updatePharmacy(any(PharmacyRegistrationRequest.class))).thenReturn(pharmacy);

        mockMvc.perform(post("/admin/pharmacy/update-pharmacy")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"email\":\"test@example.com\"}"))
                .andExpect(status().isOk());
    }
}