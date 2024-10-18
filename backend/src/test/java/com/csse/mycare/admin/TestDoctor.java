package com.csse.mycare.admin;

import com.csse.mycare.common.CalendarUtil;
import com.csse.mycare.masterservice.MasterServiceImpl;
import com.csse.mycare.masterservice.dao.Appointment;
import com.csse.mycare.masterservice.dao.Doctor;
import com.csse.mycare.masterservice.dao.Schedule;
import com.csse.mycare.masterservice.service.AppointmentService;
import com.csse.mycare.masterservice.service.DoctorService;
import com.csse.mycare.patient.dto.DoctorAvailabilityRequest;
import com.csse.mycare.patient.dto.DoctorAvailabilityResponse;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@Slf4j
public class TestDoctor {
    @Mock
    private AppointmentService appointmentService;

    @Mock
    private DoctorService doctorService;

    @InjectMocks
    private MasterServiceImpl masterService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }
}
