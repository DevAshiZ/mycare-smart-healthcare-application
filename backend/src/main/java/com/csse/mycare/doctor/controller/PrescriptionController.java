package com.csse.mycare.doctor.controller;

import com.csse.mycare.common.BaseController;
import com.csse.mycare.common.BaseResponse;
import com.csse.mycare.doctor.dto.PrescriptionDTO;
import com.csse.mycare.masterservice.dao.Prescription;
import com.fasterxml.jackson.databind.ser.Serializers;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.ParseException;

import static com.csse.mycare.common.constants.ErrorCodes.INVALID_TIME_FORMAT;
import static com.csse.mycare.common.constants.ErrorCodes.UNKNOWN_ERROR;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@Slf4j
@Controller()
@RequestMapping("/prescription")
public class PrescriptionController extends BaseController {
    @PostMapping("/issue")
    public ResponseEntity<BaseResponse<Prescription>> issuePrescription(@RequestBody PrescriptionDTO prescriptionRequest) {
        try {
            log.info("Started saving prescription info for patient: {}, issued by doctor: {}",
                    prescriptionRequest.getPatientId(), prescriptionRequest.getDoctorId());
            Prescription prescription = masterService.savePrescription(prescriptionRequest);
            return new ResponseEntity<>(new BaseResponse<>(prescription), CREATED);
        } catch (ParseException e) {
            log.error("Invalid time format : {}", prescriptionRequest.getIssueDate());
            return new ResponseEntity<>(new BaseResponse<>(INVALID_TIME_FORMAT), INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            log.error("Error adding prescription: {}", prescriptionRequest);
            return new ResponseEntity<>(new BaseResponse<>(UNKNOWN_ERROR), INTERNAL_SERVER_ERROR);
        }
    }
}
