package com.csse.mycare.admin.controller;

import com.csse.mycare.admin.dto.ScheduleRequest;
import com.csse.mycare.common.BaseController;
import com.csse.mycare.common.BaseResponse;
import com.csse.mycare.masterservice.dao.Schedule;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import static com.csse.mycare.common.ErrorCodes.UNKNOWN_ERROR;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@Controller
@Slf4j
@RequestMapping("/admin/schedule")
public class ScheduleController extends BaseController {

    @PostMapping("/add-schedule")
    public ResponseEntity<BaseResponse<Schedule>> addSchedule(@RequestBody ScheduleRequest request) {
        try {
            log.info("Adding schedule: {}", request.getDoctorId());
            Schedule result = masterService.saveSchedule(request);
            log.info("Schedule added for: {}", request.getDoctorId());
            return new ResponseEntity<>(new BaseResponse<>(result), CREATED);
        } catch (Exception e) {
            log.error("Error adding schedule: {}", request.getDoctorId(), e);
            return new ResponseEntity<>(new BaseResponse<>(UNKNOWN_ERROR), OK);
        }
    }


    @GetMapping("/get-schedules-by-day")
    public ResponseEntity<BaseResponse<Iterable<Schedule>>> getSchedulesByDay(@RequestParam String day) {
        try {
            log.info("Getting schedules for day: {}", day);
            Iterable<Schedule> result = masterService.getSchedulesByDay(day);
            log.info("Schedules fetched for day: {}", day);
            return new ResponseEntity<>(new BaseResponse<>(result), OK);
        } catch (Exception e) {
            log.error("Error fetching schedules for day: {}", day, e);
            return new ResponseEntity<>(new BaseResponse<>(UNKNOWN_ERROR), OK);
        }
    }

    @GetMapping("/get-schedules-by-doctor")
    public ResponseEntity<BaseResponse<Iterable<Schedule>>> getSchedulesByDoctor(@RequestParam Integer doctorId) {
        try {
            log.info("Getting schedules for doctor: {}", doctorId);
            Iterable<Schedule> result = masterService.getSchedulesByDoctorId(doctorId);
            log.info("Schedules fetched for doctor: {}", doctorId);
            return new ResponseEntity<>(new BaseResponse<>(result), OK);
        } catch (Exception e) {
            log.error("Error fetching schedules for doctor: {}", doctorId, e);
            return new ResponseEntity<>(new BaseResponse<>(UNKNOWN_ERROR), OK);
        }
    }
}
