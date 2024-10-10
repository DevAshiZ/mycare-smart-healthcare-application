package com.csse.mycare.patient.dto;

public record AppointmentResponse(
        String appointmentStart,
        String appointmentDuration,
        Integer patientId,
        Integer doctorId,
        Boolean isMade
) {
    @Override
    public String toString() {
        return "AppointmentResponse{" +
                ", appointmentStart='" + appointmentStart + '\'' +
                ", appointmentDuration='" + appointmentDuration + '\'' +
                ", patientId=" + patientId +
                ", doctorId=" + doctorId +
                ", isMade=" + isMade +
                '}';
    }
}