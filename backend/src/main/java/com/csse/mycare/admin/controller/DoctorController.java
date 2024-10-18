package com.csse.mycare.admin.controller;

import com.csse.mycare.admin.dto.DoctorRegistrationRequest;
import com.csse.mycare.common.BaseController;
import com.csse.mycare.common.BaseResponse;
import com.csse.mycare.common.exceptions.UserAlreadyExistsException;
import com.csse.mycare.masterservice.dao.Doctor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

import static com.csse.mycare.common.constants.ErrorCodes.DOCTOR_ALREADY_EXISTS;
import static com.csse.mycare.common.constants.ErrorCodes.UNKNOWN_ERROR;
import static org.springframework.http.HttpStatus.*;

@Slf4j
@Controller
@RequestMapping("/admin/doctor")
public class DoctorController extends BaseController {
    @PostMapping("/register-doctor")
    public ResponseEntity<BaseResponse<Boolean>> registerDoctor(@RequestBody DoctorRegistrationRequest request) {
        try {
            log.info("Registering doctor: {}", request.getEmail());
            Boolean result = masterService.saveDoctor(request);
            log.info("Doctor registration completed for: {}", request.getEmail());
            return new ResponseEntity<>(new BaseResponse<>(result), CREATED);
        } catch (UserAlreadyExistsException e) {
            log.error("Doctor registration failed for: {}", request.getEmail());
            return new ResponseEntity<>(new BaseResponse<>(DOCTOR_ALREADY_EXISTS), INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            log.error("Error registering doctor: {}", request.getEmail(), e);
            return new ResponseEntity<>(new BaseResponse<>(UNKNOWN_ERROR), INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/get-all-doctors")
    public ResponseEntity<BaseResponse<List<Doctor>>> getAllDoctors() {
        log.info("Getting all doctors");
        try {
            List<Doctor> doctors = masterService.getAllDoctors();
            log.info("Successfully fetched doctors");
            return new ResponseEntity<>(new BaseResponse<>(doctors), OK);
        } catch (Exception e) {
            log.error("Error fetching doctors", e);
            return new ResponseEntity<>(new BaseResponse<>(UNKNOWN_ERROR), INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/update-doctor")
    public ResponseEntity<BaseResponse<Doctor>> updateDoctor(@RequestBody DoctorRegistrationRequest request) {
        try {
            log.info("Updating doctor: {}", request.getEmail());
            Doctor savedDoctor = masterService.updateDoctor(request);
            log.info("Doctor update completed for: {}", request.getEmail());
            return new ResponseEntity<>(new BaseResponse<>(savedDoctor), OK);
        } catch (Exception e) {
            log.error("Error updating doctor: {}", request.getEmail(), e);
            return new ResponseEntity<>(new BaseResponse<>(UNKNOWN_ERROR), INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/delete-doctor")
    public ResponseEntity<BaseResponse<Boolean>> deleteDoctor(Integer doctorId) {
        try {
            log.info("Deleting doctor: {}", doctorId);
            masterService.deleteDoctor(doctorId);
            log.info("Doctor deletion completed for: {}", doctorId);
            return new ResponseEntity<>(new BaseResponse<>(true), OK);
        } catch (Exception e) {
            log.error("Error deleting doctor: {}", doctorId, e);
            return new ResponseEntity<>(new BaseResponse<>(UNKNOWN_ERROR), INTERNAL_SERVER_ERROR);
        }
    }
}
