package com.csse.mycare.patient.controller;

import com.csse.mycare.common.BaseController;
import com.csse.mycare.common.BaseResponse;
import com.csse.mycare.common.exceptions.AppointmentAlreadyExistsException;
import com.csse.mycare.common.exceptions.PaymentAlreadyMadeException;
import com.csse.mycare.masterservice.dao.Appointment;
import com.csse.mycare.patient.dto.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/get-appointments")
    public ResponseEntity<BaseResponse<List<Appointment>>> getPatientReservedAppointments(@RequestParam Integer patientId) {
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

    @PostMapping("payment/pay-card")
    public ResponseEntity<BaseResponse<PaymentResponse>> makeCardPayment(@RequestBody CardPaymentRequest cardPaymentRequest) {
        PaymentResponse response;
        log.info("making card payment for userId: {}", cardPaymentRequest.getUserId());

        try{
            response = masterService.makeCardPayment(cardPaymentRequest);
        }catch (PaymentAlreadyMadeException e){
            log.error("Error making card payment for userId: {}", cardPaymentRequest.getUserId(), e);
            return new ResponseEntity<>(new BaseResponse<>(PAYMENT_ALREADY_MADE), HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e){
            log.error("Error making card payment for userId: {}", cardPaymentRequest.getUserId(), e);
            return new ResponseEntity<>(new BaseResponse<>(PAYMENT_ERROR), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(
                new BaseResponse<>(response),
                HttpStatus.CREATED
        );
    }

    @PostMapping("/payment/pay-cash")
    public ResponseEntity<BaseResponse<PaymentResponse>> makeCashPayment(@RequestBody CashPaymentRequest cashPaymentRequest) {
        PaymentResponse response;
        log.info("making cash payment for userId: {}", cashPaymentRequest.getUserId());

        try{
            response = masterService.makeCashPayment(cashPaymentRequest);
        }catch (PaymentAlreadyMadeException e){
            log.error("Error making cash payment for userId: {}", cashPaymentRequest.getUserId(), e);
            return new ResponseEntity<>(new BaseResponse<>(PAYMENT_ALREADY_MADE), HttpStatus.INTERNAL_SERVER_ERROR);
        }catch (Exception e){
            log.error("Error making cash payment for userId: {}", cashPaymentRequest.getUserId(), e);
            return new ResponseEntity<>(new BaseResponse<>(PAYMENT_ERROR), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(
                new BaseResponse<>(response),
                HttpStatus.CREATED
        );
    }


}