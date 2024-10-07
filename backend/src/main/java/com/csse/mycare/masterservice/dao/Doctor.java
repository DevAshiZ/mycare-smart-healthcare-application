package com.csse.mycare.masterservice.dao;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@DiscriminatorValue("DOCTOR")
@Getter
@Setter
@AllArgsConstructor
public class Doctor extends User {
    private String specialization;
    @Column(name = "registration_number")
    private String registrationNumber;
    @OneToOne
    private Hospital hospital;
    @OneToOne
    private Schedule schedule;

    public Doctor() {
        if (schedule == null) {
            schedule = new Schedule();
        }
    }
}
