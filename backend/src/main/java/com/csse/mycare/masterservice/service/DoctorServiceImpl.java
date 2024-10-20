package com.csse.mycare.masterservice.service;

import com.csse.mycare.admin.dto.DoctorRegistrationRequest;
import com.csse.mycare.common.exceptions.ReferedDoctorNotFoundException;
import com.csse.mycare.masterservice.dao.Doctor;
import com.csse.mycare.masterservice.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorServiceImpl implements DoctorService {
    private final DoctorRepository doctorRepository;

    @Autowired
    public DoctorServiceImpl(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    @Override
    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }

    @Override
    public Doctor getDoctorById(int id) {
        return doctorRepository.getReferenceById(id);
    }

    @Override
    public Doctor getDoctorByEmail(String email) {
        return doctorRepository.getDoctorByEmail(email);
    }

    @Override
    public Doctor saveDoctor(Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    @Override
    public Doctor updateDoctor(DoctorRegistrationRequest doctor) throws ReferedDoctorNotFoundException {
        try {
            Doctor existingDoctor = doctorRepository.getReferenceById(doctor.getDoctorId());
            if (doctor.getFirstName() != null) {
                existingDoctor.setFirstName(doctor.getFirstName());
            }
            if (doctor.getLastName() != null) {
                existingDoctor.setLastName(doctor.getLastName());
            }
            if (doctor.getEmail() != null) {
                existingDoctor.setEmail(doctor.getEmail());
            }
            if (doctor.getSpecialization() != null) {
                existingDoctor.setSpecialization(doctor.getSpecialization());
            }
            return doctorRepository.save(existingDoctor);
        } catch (Exception e) {
            throw new ReferedDoctorNotFoundException();
        }
    }

    @Override
    public void deleteDoctor(int id) {
        doctorRepository.deleteById(id);
    }
}
