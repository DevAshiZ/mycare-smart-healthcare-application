package com.csse.mycare.masterservice.service;

import com.csse.mycare.admin.dto.DoctorRegistrationRequest;
import com.csse.mycare.common.exceptions.ReferedDoctorNotFoundException;
import com.csse.mycare.masterservice.dao.Doctor;

import java.util.List;

public interface DoctorService {
    List<Doctor> getAllDoctors();

    Doctor getDoctorById(int id);

    Doctor getDoctorByEmail(String email);

    Doctor saveDoctor(Doctor doctor);

    Doctor updateDoctor(DoctorRegistrationRequest doctor) throws ReferedDoctorNotFoundException;

    void deleteDoctor(int id);
}
