package com.csse.mycare.masterservice.dao;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private Date appointmentStart;
    private Integer duration;
    @OneToOne(fetch = FetchType.EAGER)
    private Doctor doctor;
    @OneToOne(fetch = FetchType.EAGER)
    private Payment payment;
    @OneToOne(fetch = FetchType.EAGER)
    private Patient patient;
}
