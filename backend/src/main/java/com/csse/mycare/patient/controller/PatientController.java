package com.csse.mycare.patient.controller;

import com.csse.mycare.common.BaseController;
import com.csse.mycare.masterservice.MasterService;
import com.csse.mycare.masterservice.dao.Appointment;
import com.csse.mycare.masterservice.dao.Doctor;
import com.csse.mycare.patient.dto.AppointmentRequest;
import com.csse.mycare.patient.dto.AppointmentResponse;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.print.Doc;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.http.HttpStatus.OK;

@Controller
@RequestMapping("/patient")
public class PatientController extends BaseController {
    @PostMapping("/getAllDoctors")
    public ResponseEntity<Map<Doctor, List<Appointment>>> getAllDoctors() {
        // TODO: Get appointments by schedule and day
        Map<Doctor, List<Appointment>> doctorAppointments = new HashMap<>();
        List<Doctor> doctors = masterService.getAllDoctors();
        for (Doctor doctor : doctors) {
            List<Appointment> appointments = masterService.getAppointmentsBySchedule(doctor.getSchedule().getId());
            doctorAppointments.put(doctor, appointments);
        }
        return new ResponseEntity<>(doctorAppointments, OK);
    }

    @PostMapping("/makeAppointment")
    public ResponseEntity<AppointmentResponse> makeAppointment(@RequestBody AppointmentRequest appointmentRequest) {
        return null;
    }
}
