package com.csse.mycare.patient.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class DoctorAvailabilityRequest {
    private Integer doctorId;
    private Date date;
}
