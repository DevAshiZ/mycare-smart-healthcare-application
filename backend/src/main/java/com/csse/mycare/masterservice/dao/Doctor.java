package com.csse.mycare.masterservice.dao;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@DiscriminatorValue("DOCTOR")
@SuperBuilder
@Getter
@Setter
@AllArgsConstructor
public class Doctor extends User {
    private String specialization;
    @Column(name = "registration_number")
    private String registrationNumber;
    @OneToOne
    private Hospital hospital;
    @OneToOne(fetch = FetchType.EAGER)
    private Schedule schedule;

    public Doctor() {
        if (schedule == null) {
            schedule = new Schedule();
        }
    }

    public Doctor(User user) {
        super(user);
    }
}
