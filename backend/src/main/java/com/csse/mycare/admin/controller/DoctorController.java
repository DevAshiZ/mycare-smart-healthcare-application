package com.csse.mycare.admin.controller;

import com.csse.mycare.admin.dto.DoctorRegistrationRequest;
import com.csse.mycare.common.BaseController;
import com.csse.mycare.common.BaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/admin/doctor")
public class DoctorController extends BaseController {
    @PostMapping("/register-doctor")
    public BaseResponse<Boolean> registerDoctor(DoctorRegistrationRequest request) {
        try {
            log.info("Registering doctor: {}", request.getEmail());
            Boolean result = masterService.saveDoctor(request);
            log.info("Doctor registered successfully: {}", request.getEmail());
            return new BaseResponse<>(result);
        } catch (Exception e) {
            log.error("Error registering doctor: {}", request.getEmail(), e);
            return new BaseResponse<>(false);
        }
    }
}
