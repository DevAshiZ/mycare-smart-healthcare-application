package com.csse.mycare.patient.dto;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentResponse {
    private String transactionId;
    private String paymentMethod;
    private String appointmentID;
    private LocalDateTime paymentDateTime;
    private Boolean isPaid;
    private BigDecimal paymentAmount;
}
