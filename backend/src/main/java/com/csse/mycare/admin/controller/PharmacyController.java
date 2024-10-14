package com.csse.mycare.admin.controller;

import com.csse.mycare.admin.dto.PharmacyRegistrationRequest;
import com.csse.mycare.common.BaseController;
import com.csse.mycare.common.BaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequestMapping("/admin/pharmacy")
public class PharmacyController extends BaseController {
    @PostMapping("/register-pharmacy")
    public ResponseEntity<BaseResponse<Boolean>> registerPharmacy(PharmacyRegistrationRequest request) {
        try {
            log.info("Registering pharmacy: {}", request.getEmail());
            Boolean result = masterService.savePharmacy(request);
            log.info("Pharmacy registered successfully: {}", request.getEmail());
            return new ResponseEntity<>(new BaseResponse<>(true), HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error registering pharmacy: {}", request.getEmail(), e);
            return new ResponseEntity<>(new BaseResponse<>(false), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
