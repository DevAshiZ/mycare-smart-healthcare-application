package com.csse.mycare.masterservice.service;

import com.csse.mycare.masterservice.dao.Prescription;

import java.util.List;

public interface PrescriptionService {
    Prescription findPrescriptionById(Long id);

    List<Prescription> findAllPrescriptions();

    Prescription savePrescription(Prescription prescription);

    void deletePrescription(Integer prescription);
}
