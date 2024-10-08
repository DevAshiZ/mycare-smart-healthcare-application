package com.csse.mycare.masterservice.dao;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@DiscriminatorValue("PATIENT")
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class Patient extends User {
    private Integer age;
    private String gender;
}
