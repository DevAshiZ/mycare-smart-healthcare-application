package com.csse.mycare.masterservice.service;

import com.csse.mycare.common.exceptions.InvalidUpdateUserException;
import com.csse.mycare.masterservice.dao.Patient;
import com.csse.mycare.masterservice.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PatientServiceImpl implements PatientService{
    PatientRepository patientRepository;

    @Autowired
    public PatientServiceImpl(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @Override
    public Patient getPatient(Integer id) {
        return patientRepository.findById(id).orElse(null);
    }

    @Override
    public Patient updatePatient(Patient patient) throws InvalidUpdateUserException {
        if (patientRepository.existsById(patient.getUserId())) {
            return patientRepository.save(patient);
        } else {
            throw new InvalidUpdateUserException();
        }
    }

    @Override
    public void deletePatient(Integer id) {

    }

    @Override
    public List<Patient> getAllPatients() {
        return List.of();
    }

    @Override
    public List<Patient> getPatientsByDoctor(Integer doctorId) {
        return List.of();
    }

    @Override
    public List<Patient> getPatientsBySchedule(Integer scheduleId) {
        return List.of();
    }

    @Override
    public List<Patient> getPatientsByScheduleAndDay(Integer scheduleId, Date date) {
        return List.of();
    }
}
