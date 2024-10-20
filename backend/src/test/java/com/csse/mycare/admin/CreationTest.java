package com.csse.mycare.admin;

import com.csse.mycare.admin.controller.DoctorController;
import com.csse.mycare.admin.controller.PharmacyController;
import com.csse.mycare.admin.controller.ScheduleController;
import com.csse.mycare.admin.dto.DoctorRegistrationRequest;
import com.csse.mycare.admin.dto.PharmacyRegistrationRequest;
import com.csse.mycare.admin.dto.ScheduleRequest;
import com.csse.mycare.common.exceptions.UserAlreadyExistsException;
import com.csse.mycare.masterservice.MasterService;
import com.csse.mycare.masterservice.dao.Schedule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class CreationTest {

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
    public void testRegisterDoctor_Success() throws Exception {
        DoctorRegistrationRequest request = new DoctorRegistrationRequest();
        request.setEmail("test@example.com");

        when(masterService.saveDoctor(any(DoctorRegistrationRequest.class))).thenReturn(true);

        mockMvc.perform(post("/admin/doctor/register-doctor")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"email\":\"test@example.com\"}"))
                .andExpect(status().isCreated());
    }

    @Test
    public void testRegisterDoctor_UserAlreadyExists() throws Exception {
        DoctorRegistrationRequest request = new DoctorRegistrationRequest();
        request.setEmail("test@example.com");

        doThrow(new UserAlreadyExistsException("")).when(masterService).saveDoctor(any(DoctorRegistrationRequest.class));

        mockMvc.perform(post("/admin/doctor/register-doctor")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"email\":\"test@example.com\"}"))
                .andExpect(status().isInternalServerError());
    }

    @Test
    public void testRegisterPharmacy_Success() throws Exception {
        PharmacyRegistrationRequest request = new PharmacyRegistrationRequest();
        request.setEmail("test@example.com");

        when(masterService.savePharmacy(any(PharmacyRegistrationRequest.class))).thenReturn(true);

        mockMvc.perform(post("/admin/pharmacy/register-pharmacy")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"email\":\"test@example.com\"}"))
                .andExpect(status().isCreated());
    }

    @Test
    public void testRegisterPharmacy_UserAlreadyExists() throws Exception {
        PharmacyRegistrationRequest request = new PharmacyRegistrationRequest();
        request.setEmail("test@example.com");

        doThrow(new UserAlreadyExistsException("")).when(masterService).savePharmacy(any(PharmacyRegistrationRequest.class));

        mockMvc.perform(post("/admin/pharmacy/register-pharmacy")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"email\":\"test@example.com\"}"))
                .andExpect(status().isInternalServerError());
    }

    @Test
    public void testAddSchedule_Success() throws Exception {
        ScheduleRequest request = new ScheduleRequest();
        request.setDoctorId(String.valueOf(1));

        when(masterService.saveSchedule(any(ScheduleRequest.class))).thenReturn(new Schedule());

        mockMvc.perform(post("/admin/schedule/add-schedule")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"doctorId\":1}"))
                .andExpect(status().isCreated());
    }
}