package com.csse.mycare.admin;

import com.csse.mycare.admin.controller.DoctorController;
import com.csse.mycare.admin.controller.PharmacyController;
import com.csse.mycare.admin.controller.ScheduleController;
import com.csse.mycare.masterservice.MasterService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class DeleteTest {

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
    public void testDeleteDoctor_Success() throws Exception {
        doNothing().when(masterService).deleteDoctor(1);

        mockMvc.perform(post("/admin/doctor/delete-doctor")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("doctorId", "1"))
                .andExpect(status().isOk());
    }

    @Test
    public void testDeleteDoctor_Failure() throws Exception {
        doThrow(new RuntimeException("Error deleting doctor")).when(masterService).deleteDoctor(1);

        mockMvc.perform(post("/admin/doctor/delete-doctor")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("doctorId", "1"))
                .andExpect(status().isInternalServerError());
    }

    @Test
    public void testDeletePharmacy_Success() throws Exception {
        doNothing().when(masterService).deletePharmacy(1);

        mockMvc.perform(post("/admin/pharmacy/delete-pharmacy")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("id", "1"))
                .andExpect(status().isOk());
    }

    @Test
    public void testDeletePharmacy_Failure() throws Exception {
        doThrow(new RuntimeException("Error deleting pharmacy")).when(masterService).deletePharmacy(1);

        mockMvc.perform(post("/admin/pharmacy/delete-pharmacy")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("id", "1"))
                .andExpect(status().isInternalServerError());
    }
}