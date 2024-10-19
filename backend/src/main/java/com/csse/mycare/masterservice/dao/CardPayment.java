package com.csse.mycare.masterservice.dao;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class CardPayment extends Payment{
    private String cardNumber;
    private String cardHolderName;
    private String expiryDate;
    private String cvv;
}
