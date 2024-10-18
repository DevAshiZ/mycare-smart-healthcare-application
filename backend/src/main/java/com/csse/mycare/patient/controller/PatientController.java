package com.csse.mycare.patient.controller;

import com.csse.mycare.common.BaseController;
import com.csse.mycare.common.BaseResponse;
import com.csse.mycare.masterservice.dao.Appointment;
import com.csse.mycare.patient.dto.AppointmentRequest;
import com.csse.mycare.patient.dto.AppointmentResponse;
import com.csse.mycare.patient.dto.DoctorAvailabilityRequest;
import com.csse.mycare.patient.dto.DoctorAvailabilityResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

import static com.csse.mycare.common.ErrorCodes.APPOINTMENT_ADD_ERROR;
import static org.springframework.http.HttpStatus.OK;

@Slf4j
@Controller
@RequestMapping("/patient")
public class PatientController extends BaseController {
    @PostMapping("/create-appointment")
    public ResponseEntity<BaseResponse<AppointmentResponse>> createAppointment(@RequestBody AppointmentRequest appointmentRequest) {
        AppointmentResponse response;
        log.info("creating appointment for patientId: {}", appointmentRequest.getPatientId());
        try {
            response = masterService.createAppointmentWithDoctor(appointmentRequest);
        } catch (Exception e) {
            log.error("Error creating appointment", e);
            return new ResponseEntity<>(new BaseResponse<>(
                    null,
                    false,
                    APPOINTMENT_ADD_ERROR.getCode(),
                    APPOINTMENT_ADD_ERROR.getMessage()
            ), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        log.info("creating appointment for patientId: {}", appointmentRequest.getPatientId());
        return new ResponseEntity<>(
                new BaseResponse<>(response),
                OK
        );
    }

    @PostMapping("/get-appointments")
    public ResponseEntity<List<Appointment>> getPatientReservedAppointments(Integer patientId) {
        log.info("getting appointments for patientId: {}", patientId);
        return new ResponseEntity<>(masterService.getAppointmentsByPatient(patientId), OK);
    }
}
