package com.csse.mycare.patient.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CashPaymentRequest {
    private Integer userId;
    private Integer appointmentId;
    private BigDecimal amount;
    private String userEmail;
}
