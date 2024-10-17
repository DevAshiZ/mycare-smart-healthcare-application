package com.csse.mycare.masterservice.service;

import com.csse.mycare.admin.dto.ScheduleRequest;
import com.csse.mycare.masterservice.dao.Schedule;

import java.util.List;

public interface ScheduleService {

    Schedule saveSchedule(ScheduleRequest schedule);
//    public List<Schedule> getSchedulesByDoctor(Integer doctorId);
    Schedule updateSchedule(Schedule schedule);
    List<Schedule> getSchedulesByDay(String day);
}
