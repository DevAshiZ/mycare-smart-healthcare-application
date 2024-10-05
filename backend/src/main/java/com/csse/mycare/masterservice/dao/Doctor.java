package com.csse.mycare.masterservice.dao;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@DiscriminatorValue("DOCTOR")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Doctor extends User {
    private String specialization;
    @Column(name = "registration_number")
    private String registrationNumber;
    @OneToOne
    private Hospital hospital;
}
