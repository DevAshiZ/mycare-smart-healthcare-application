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
public class TestAdmin {
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

    @Test
    public void testGetAppointmentsByScheduleAndDay() {
        List<Appointment> appointments = new ArrayList<>();
        Appointment appointment1 = new Appointment();
        appointment1.setAppointmentStart(new Date());
        appointment1.setDuration(30);
        appointments.add(appointment1);

        Appointment appointment2 = new Appointment();
        appointment2.setAppointmentStart(new Date());
        appointment2.setDuration(30);
        appointments.add(appointment2);

        when(appointmentService.getAppointmentsByScheduleAndDay(anyInt(), any(Date.class))).thenReturn(appointments);

        List<Appointment> result = masterService.getAppointmentsByScheduleAndDay(1, new Date());
        assertNotNull(result);
        assertEquals(2, result.size());
        log.info("Appointments are not null and size is correct");

        int appointmentTime = 0;
        for (Appointment appointment : appointments) {
            appointmentTime += appointment.getDuration();
        }
        assertEquals(60, appointmentTime);

        log.info("Successfully executed the test case: Get Appointments By Schedule And Day");
    }

    @Test
    public void testGetDoctorAvailableDates_noAppointments() {
        DoctorAvailabilityRequest request = new DoctorAvailabilityRequest();
        request.setDoctorId(1);
        request.setDate(new Date());

        Doctor doctor = new Doctor();
        doctor.setSchedule(new Schedule());
        when(doctorService.getDoctorById(1)).thenReturn(doctor);
        when(appointmentService.getAppointmentsByScheduleAndDay(anyInt(), any(Date.class))).thenReturn(Collections.emptyList());

        DoctorAvailabilityResponse response = masterService.getDoctorAvailableDates(request);

        assertNotNull(response);
        log.info("Response is not null");
        assertEquals(doctor, response.getDoctor());
        log.info("Returned Doctor is the same as the input doctor");
        assertTrue(response.getFreeSlots().isEmpty());
        log.info("Successfully executed the test case: No Appointments");
    }

    @Test
    public void testGetDoctorAvailableDates_withAppointments() {
        DoctorAvailabilityRequest request = new DoctorAvailabilityRequest();
        request.setDoctorId(1);
        request.setDate(new Date());

        Doctor doctor = new Doctor();
        doctor.setSchedule(new Schedule());
        when(doctorService.getDoctorById(1)).thenReturn(doctor);

        Appointment appointment1 = new Appointment();
        appointment1.setAppointmentStart(CalendarUtil.addMinutes(CalendarUtil.getStartOfDay(request.getDate()), 60));
        appointment1.setDuration(30);

        Appointment appointment2 = new Appointment();
        appointment2.setAppointmentStart(CalendarUtil.addMinutes(CalendarUtil.getStartOfDay(request.getDate()), 120));
        appointment2.setDuration(30);

        List<Appointment> appointments = Arrays.asList(appointment1, appointment2);
        when(masterService.getAppointmentsByScheduleAndDay(anyInt(), any(Date.class))).thenReturn(appointments);
        DoctorAvailabilityResponse response = masterService.getDoctorAvailableDates(request);

        assertNotNull(response);
        log.info("Availability Response is not null");
        assertEquals(doctor, response.getDoctor());
        log.info("Returned Doctor is the same as the input doctor");
        assertFalse(response.getFreeSlots().isEmpty());
        int appointmentTime = 0;
        int freeTime = 1440;
        for (Appointment appointment : appointments) {
            appointmentTime += appointment.getDuration();
        }
        for (Map.Entry<Date, Integer> entry : response.getFreeSlots().entrySet()) {
            freeTime -= entry.getValue();
        }
        assertEquals(60, appointmentTime);
        log.info("Total appointment time is correct: {}", appointmentTime);
        // 1440 minutes in a day, 60 minutes for the appointment
        assertEquals(1440 - appointmentTime, freeTime);
        log.info("Total free time is correct: {}", freeTime);
    }
}
