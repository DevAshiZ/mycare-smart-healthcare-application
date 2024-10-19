package com.csse.mycare.patient.dto;

import com.csse.mycare.common.constants.PaymentMethod;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CardPaymentRequest {
    private Integer userId;
    private Integer appointmentId;
    private BigDecimal amount;
    private String cardNumber;
    private String cardHolderName;
    private String expiryDate;
    private String cvv;
}
