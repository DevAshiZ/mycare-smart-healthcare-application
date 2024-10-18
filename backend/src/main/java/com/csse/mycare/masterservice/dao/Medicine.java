package com.csse.mycare.masterservice.dao;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Medicine {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "medicine_id")
    private Integer medicineId;

    @Column(name = "medicine_name")
    private String medicineName;

    // Integer value in miligrams
    private Integer dosage;
    private String frequency;
    private String duration;

    @Lob
    private String instructions;
}
