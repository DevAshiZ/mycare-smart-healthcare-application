package com.csse.mycare.masterservice.dao;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("PHARMACY")
public class Pharmacy extends User {
}
