package com.csse.mycare.masterservice.service;

import com.csse.mycare.common.exceptions.InvalidUpdateUserException;
import com.csse.mycare.masterservice.dao.Patient;

import java.util.Date;
import java.util.List;

public interface PatientService {
    Patient getPatient(Integer id);
    Patient updatePatient(Patient patient) throws InvalidUpdateUserException;
    void deletePatient(Integer id);
    List<Patient> getAllPatients();
    List<Patient> getPatientsByDoctor(Integer doctorId);
    List<Patient> getPatientsBySchedule(Integer scheduleId);
    public List<Patient> getPatientsByScheduleAndDay(Integer scheduleId, Date date);
}
