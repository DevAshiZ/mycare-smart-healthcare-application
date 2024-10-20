package com.csse.mycare.admin.controller;

import com.csse.mycare.admin.dto.PharmacyRegistrationRequest;
import com.csse.mycare.common.BaseController;
import com.csse.mycare.common.BaseResponse;
import com.csse.mycare.common.exceptions.UserAlreadyExistsException;
import com.csse.mycare.masterservice.dao.Pharmacy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

import static com.csse.mycare.common.constants.ErrorCodes.UNKNOWN_ERROR;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.OK;

@Controller
@Slf4j
@RequestMapping("/admin/pharmacy")
public class PharmacyController extends BaseController {
    @PostMapping("/register-pharmacy")
    public ResponseEntity<BaseResponse<Boolean>> registerPharmacy(@RequestBody  PharmacyRegistrationRequest request) {
        try {
            log.info("Registering pharmacy: {}", request.getEmail());
            Boolean result = masterService.savePharmacy(request);
            log.info("Pharmacy registered successfully: {}", request.getEmail());
            return new ResponseEntity<>(new BaseResponse<>(result), HttpStatus.CREATED);
        } catch (UserAlreadyExistsException e) {
            log.error("Pharmacy registration failed for: {}", request.getEmail());
            return new ResponseEntity<>(new BaseResponse<>(false, e.getMessage()), INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            log.error("Error registering Pharmacy: {}", request.getEmail(), e);
            return new ResponseEntity<>(new BaseResponse<>(UNKNOWN_ERROR), INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/get-all-pharmacies")
    public ResponseEntity<BaseResponse<List<Pharmacy>>> getAllPharmacies() {
        log.info("Getting all pharmacies");
        try {
            List<Pharmacy> pharmacies = masterService.getAllPharmacies();
            log.info("Successfully fetched pharmacies");
            return new ResponseEntity<>(new BaseResponse<>(pharmacies), OK);
        } catch (Exception e) {
            log.error("Error fetching pharmacies", e);
            return new ResponseEntity<>(new BaseResponse<>(UNKNOWN_ERROR), INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/update-pharmacy")
    public ResponseEntity<BaseResponse<Pharmacy>> updatePharmacy(@RequestBody PharmacyRegistrationRequest request) {
        try {
            log.info("Updating pharmacy: {}", request.getEmail());
            Pharmacy savedPharmacy = masterService.updatePharmacy(request);
            log.info("Pharmacy updated successfully: {}", request.getEmail());
            return new ResponseEntity<>(new BaseResponse<>(savedPharmacy), OK);
        } catch (Exception e) {
            log.error("Error updating pharmacy: {}", request.getEmail(), e);
            return new ResponseEntity<>(new BaseResponse<>(UNKNOWN_ERROR), INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/delete-pharmacy")
    public ResponseEntity<BaseResponse<Boolean>> deletePharmacy(Integer id) {
        try {
            log.info("Deleting pharmacy: {}", id);
            masterService.deletePharmacy(id);
            log.info("Pharmacy deleted successfully: {}", id);
            return new ResponseEntity<>(new BaseResponse<>(true), OK);
        } catch (Exception e) {
            log.error("Error deleting pharmacy: {}", id, e);
            return new ResponseEntity<>(new BaseResponse<>(UNKNOWN_ERROR), INTERNAL_SERVER_ERROR);
        }
    }
}
