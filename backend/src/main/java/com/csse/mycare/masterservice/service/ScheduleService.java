package com.csse.mycare.masterservice.service;

import com.csse.mycare.admin.dto.ScheduleRequest;
import com.csse.mycare.masterservice.dao.Schedule;

public interface ScheduleService {

    Schedule saveSchedule(ScheduleRequest schedule);
//    public List<Schedule> getSchedulesByDoctor(Integer doctorId);
    Schedule updateSchedule(Schedule schedule);
}
