package com.csse.mycare.masterservice.service;

import com.csse.mycare.admin.dto.ScheduleRequest;
import com.csse.mycare.masterservice.dao.Schedule;
import com.csse.mycare.masterservice.repository.ScheduleRepository;
import lombok.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Builder
public class ScheduleServiceImpl implements ScheduleService{
    ScheduleRepository scheduleRepository;

    @Autowired
    public ScheduleServiceImpl(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }



//    @Override
//    public List<Schedule> getSchedulesByDoctor(Integer doctorId) {
//        return getSchedulesByDoctor( doctorId);
//    }

    @Override
    public Schedule saveSchedule(ScheduleRequest scheduleRequest) {

        Schedule schedule = Schedule.builder()
                .doctorId(Integer.valueOf(scheduleRequest.getDoctorId()))
                .day(scheduleRequest.getDay())
                .startTime(scheduleRequest.getStartTime())
                .endTime(scheduleRequest.getEndTime())
                .room(scheduleRequest.getRoom())
                .maxAppointments(Integer.valueOf(scheduleRequest.getMaxAppointments()))
                .build();

        return scheduleRepository.save(schedule);
    }

    @Override
    public Schedule updateSchedule(Schedule schedule) {
        return null;
    }

    @Override
    public List<Schedule> getSchedulesByDay(String day) {
        return (scheduleRepository.getScheduleByDay(day));
    }
}
