package com.csse.mycare.masterservice.repository;

import com.csse.mycare.masterservice.dao.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Integer> {
}
