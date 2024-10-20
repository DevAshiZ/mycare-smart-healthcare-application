package com.csse.mycare.masterservice.dao;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Prescription {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "prescription_id")
    private Integer prescriptionId;

    @ManyToOne
    private Patient patient;
    @ManyToOne
    private Doctor doctor;
    @Column(name = "issue_date")
    private Date issueDate;

    @OneToMany(mappedBy = "medicineId", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Medicine> medicines = new ArrayList<>();
}
