package com.csse.mycare.security.dto;

import com.csse.mycare.admin.dto.BaseRegistrationRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PatientRegisterRequest implements BaseRegistrationRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String age;
    private String gender;

    @Override
    public String toString() {
        return "RegisterRequest{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", age='" + age + '\'' +
                '}';
    }
}
