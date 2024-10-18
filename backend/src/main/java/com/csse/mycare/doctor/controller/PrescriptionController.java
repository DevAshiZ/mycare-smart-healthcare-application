package com.csse.mycare.doctor.controller;

import com.csse.mycare.common.BaseController;
import com.csse.mycare.doctor.dto.PrescriptionDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller("/prescription")
public class PrescriptionController extends BaseController {
    @PostMapping("/issue")
    public void issuePrescription(@RequestBody PrescriptionDTO prescriptionRequest) {

    }
}
