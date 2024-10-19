package com.csse.mycare.doctor.dto;

import com.csse.mycare.masterservice.dao.Medicine;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PrescriptionDTO {
    Integer patientId;
    Integer doctorId;
    String issueDate;
    List<Medicine> medicines;
}
