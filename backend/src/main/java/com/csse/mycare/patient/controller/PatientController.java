package com.csse.mycare.patient.controller;

import com.csse.mycare.common.BaseController;
import com.csse.mycare.masterservice.dao.Appointment;
import com.csse.mycare.patient.dto.AppointmentRequest;
import com.csse.mycare.patient.dto.AppointmentResponse;
import com.csse.mycare.patient.dto.DoctorAvailabilityRequest;
import com.csse.mycare.patient.dto.DoctorAvailabilityResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@Slf4j
@Controller
@RequestMapping("/patient")
public class PatientController extends BaseController {
    @PostMapping("/doctor-availability")
    public ResponseEntity<DoctorAvailabilityResponse> getDoctorAvailableDates(@RequestBody DoctorAvailabilityRequest request) {
        log.info("searching doctor availability for doctorId: {}", request.getDoctorId());
        return new ResponseEntity<>(masterService.getDoctorAvailableDates(request), OK);
    }

    @PostMapping("/create-appointment")
    public ResponseEntity<AppointmentResponse> createAppointment(@RequestBody AppointmentRequest appointmentRequest) {
        log.info("creating appointment for patientId: {}", appointmentRequest.getPatientId());
        log.info("creating appointment for doctorId: {}, start: {}, length: {}, patientId: {}", appointmentRequest.getDoctorId(), appointmentRequest.getAppointmentStart(), appointmentRequest.getAppointmentLength(), appointmentRequest.getPatientId());
        try {
            return new ResponseEntity<>(masterService.createAppointmentWithDoctor(appointmentRequest), OK);
        } catch (Exception e) {
            log.error("Error creating appointment", e);
            return new ResponseEntity<>(new AppointmentResponse(
                    null, null, null, null, false), OK);
        }
    }

    @PostMapping("/get-appointments")
    public ResponseEntity<List<Appointment>> getPatientReservedAppointments(Integer patientId) {
        log.info("getting appointments for patientId: {}", patientId);
        return new ResponseEntity<>(masterService.getAppointmentsByPatient(patientId), OK);
    }
}
