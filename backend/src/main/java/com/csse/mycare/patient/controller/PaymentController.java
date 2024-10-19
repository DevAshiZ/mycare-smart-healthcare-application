package com.csse.mycare.patient.controller;

import com.csse.mycare.common.BaseController;
import com.csse.mycare.common.BaseResponse;
import com.csse.mycare.patient.dto.CardPaymentRequest;
import com.csse.mycare.patient.dto.CashPaymentRequest;
import com.csse.mycare.patient.dto.PaymentResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import static com.csse.mycare.common.constants.ErrorCodes.PAYMENT_ERROR;

@Slf4j
@Controller
@RequestMapping("/patient/payment")
public class PaymentController extends BaseController {

    @PostMapping("/card")
    public ResponseEntity<BaseResponse<PaymentResponse>> makeCardPayment(@RequestBody CardPaymentRequest cardPaymentRequest) {
        PaymentResponse response;
        log.info("making card payment for userId: {}", cardPaymentRequest.getUserId());

        try{
            response = masterService.makeCardPayment(cardPaymentRequest);
        }catch (Exception e){
            log.error("Error making card payment for userId: {}", cardPaymentRequest.getUserId(), e);
            return new ResponseEntity<>(new BaseResponse<>(PAYMENT_ERROR), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(
                new BaseResponse<>(response),
                HttpStatus.CREATED
        );
    }

    @PostMapping("/cash")
    public ResponseEntity<BaseResponse<PaymentResponse>> makeCashPayment(@RequestBody CashPaymentRequest cashPaymentRequest) {
        PaymentResponse response;
        log.info("making cash payment for userId: {}", cashPaymentRequest.getUserId());

        try{
            response = masterService.makeCashPayment(cashPaymentRequest);
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
