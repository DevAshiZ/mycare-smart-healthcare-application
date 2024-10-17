package com.csse.mycare.admin.controller;

import com.csse.mycare.admin.dto.DoctorRegistrationRequest;
import com.csse.mycare.common.BaseController;
import com.csse.mycare.common.BaseResponse;
import com.csse.mycare.common.exceptions.UserAlreadyExistsException;
import com.csse.mycare.masterservice.dao.Doctor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

import static javax.security.auth.callback.ConfirmationCallback.OK;

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
            return new ResponseEntity<>(new BaseResponse<>(result), HttpStatus.OK);
        } catch (UserAlreadyExistsException e) {
            log.error("Doctor registration failed for: {}", request.getEmail());
            return new ResponseEntity<>(new BaseResponse<>(false, e.getMessage()), HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error registering doctor: {}", request.getEmail(), e);
            return new ResponseEntity<>(new BaseResponse<>(false, e.getMessage()), HttpStatus.OK);
        }
    }

    @GetMapping("/get-all-doctors")
    public ResponseEntity<List<Doctor>> getAllDoctors(){
        log.info("Getting all doctors");
        List<Doctor> doctors = masterService.getAllDoctors();

        return new ResponseEntity<>(doctors, HttpStatus.OK);
    }
}
