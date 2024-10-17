package com.csse.mycare.masterservice.repository;

import com.csse.mycare.masterservice.dao.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Integer> {
    public List<Schedule> getSchedulesByDoctorId(Integer doctorId);
    public List<Schedule> getSchedulesByDoctorIdAndDay(Integer doctorId, String day);
    public List<Schedule> getScheduleByDay(String day);
}
