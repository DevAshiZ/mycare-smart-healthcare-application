package com.csse.mycare.masterservice.repository;

import com.csse.mycare.masterservice.dao.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepository extends JpaRepository<Schedule, Integer> {
}
