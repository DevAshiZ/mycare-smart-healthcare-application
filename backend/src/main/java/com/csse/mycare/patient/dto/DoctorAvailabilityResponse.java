package com.csse.mycare.patient.dto;

import com.csse.mycare.masterservice.dao.Doctor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.Map;

@Getter
@Setter
public class DoctorAvailabilityResponse {
    Doctor doctor;
    Date date;
    Map<Date, Integer> freeSlots;
}
