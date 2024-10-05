package com.csse.mycare.masterservice.dao;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Hospital {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer hospitalId;
    private String hospitalName;
    private String hospitalAddress;
    private String hospitalContact;
    private String hospitalEmail;
    @OneToOne
    private User hospitalAdmin;
}
