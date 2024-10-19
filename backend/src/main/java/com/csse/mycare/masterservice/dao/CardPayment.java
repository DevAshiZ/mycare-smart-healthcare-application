package com.csse.mycare.masterservice.dao;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class CardPayment extends Payment{
    private String cardNumber;
    private String cardHolderName;
    private String expiryDate;
    private String cvv;
}
