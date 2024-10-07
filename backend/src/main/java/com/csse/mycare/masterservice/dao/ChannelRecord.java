package com.csse.mycare.masterservice.dao;

import jakarta.persistence.*;

@Entity
public class ChannelRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @OneToOne
    private Appointment appointment;
    private Boolean isChannelled = false;
}
