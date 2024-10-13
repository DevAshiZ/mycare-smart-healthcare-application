package com.csse.mycare.masterservice.dao;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@Setter
@SuperBuilder
@DiscriminatorValue("PHARMACY")
@AllArgsConstructor
@NoArgsConstructor
public class Pharmacy extends User {
    private String pharmacyName;
    private String pharmacyAddress;
}
