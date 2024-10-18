package com.csse.mycare.admin.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DoctorRegistrationRequest implements BaseRegistrationRequest {
    private Integer doctorId;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String specialization;
    private String registrationNumber;
}
