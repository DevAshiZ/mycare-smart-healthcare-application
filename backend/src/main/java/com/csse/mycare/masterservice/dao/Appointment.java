package com.csse.mycare.masterservice.dao;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private LocalDate appointmentStart;
    private Integer duration;
    @OneToOne(fetch = FetchType.EAGER)
    private Schedule schedule;
    @OneToOne(fetch = FetchType.EAGER)
    private Payment payment;
}
