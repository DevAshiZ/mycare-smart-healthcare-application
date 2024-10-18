package com.csse.mycare.admin.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ScheduleRequest {
    private String doctorId;
    private String day;
    private String room;
    private String startTime;
    private Integer duration;
    private String maxAppointments;
}
