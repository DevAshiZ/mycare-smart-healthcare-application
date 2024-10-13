package com.csse.mycare.admin.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PharmacyRegistrationRequest implements BaseRegistrationRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String pharmacyName;
    private String pharmacyAddress;
}
