package com.csse.mycare.patient.controller;

import com.csse.mycare.common.BaseController;
import com.csse.mycare.common.BaseResponse;
import com.csse.mycare.common.exceptions.AppointmentAlreadyExistsException;
import com.csse.mycare.masterservice.dao.Appointment;
import com.csse.mycare.patient.dto.AppointmentRequest;
import com.csse.mycare.patient.dto.AppointmentResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

import static com.csse.mycare.common.constants.ErrorCodes.*;
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
        } catch (AppointmentAlreadyExistsException e) {
            log.error("Error creating appointment for patientId: {}", appointmentRequest.getPatientId());
            return new ResponseEntity<>(new BaseResponse<>(APPOINTMENT_ALREADY_EXISTS), HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            log.error("Error creating appointment", e);
            return new ResponseEntity<>(new BaseResponse<>(APPOINTMENT_ADD_ERROR), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        log.info("creating appointment for patientId: {}", appointmentRequest.getPatientId());
        return new ResponseEntity<>(
                new BaseResponse<>(response),
                OK
        );
    }

    @PostMapping("/get-appointments")
    public ResponseEntity<BaseResponse<List<Appointment>>> getPatientReservedAppointments(Integer patientId) {
        log.info("getting appointments for patientId: {}", patientId);
        List<Appointment> appointments;
        try {
            appointments = masterService.getAppointmentsByPatient(patientId);
            log.info("successfully retrieved appointments for patientId: {}", patientId);
            return new ResponseEntity<>(
                    new BaseResponse<>(appointments),
                    OK
            );
        } catch (Exception e) {
            log.error("Error fetching appointments for patientId: {}", patientId, e);
            return new ResponseEntity<>(new BaseResponse<>(UNKNOWN_ERROR), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}