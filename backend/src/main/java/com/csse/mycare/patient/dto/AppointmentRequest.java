package com.csse.mycare.patient.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AppointmentRequest {
    private String appointmentStart;
    private String appointmentEnd;
    private Integer patientId;
    private Integer doctorId;

    @Override
    public String toString() {
        return "AppointmentRequest{" +
                "appointmentStart='" + appointmentStart + '\'' +
                ", appointmentEnd='" + appointmentEnd + '\'' +
                ", patientId=" + patientId +
                ", doctorId=" + doctorId +
                '}';
    }
}
