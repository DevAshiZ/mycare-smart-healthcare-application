package com.csse.mycare.patient.dto;

public record AppointmentResponse(
        Integer id,
        String appointmentStart,
        String appointmentEnd,
        Integer patientId,
        Integer doctorId,
        Boolean isMade
) {
    @Override
    public String toString() {
        return "AppointmentResponse{" +
                "id=" + id +
                ", appointmentStart='" + appointmentStart + '\'' +
                ", appointmentEnd='" + appointmentEnd + '\'' +
                ", patientId=" + patientId +
                ", doctorId=" + doctorId +
                ", isMade=" + isMade +
                '}';
    }
}