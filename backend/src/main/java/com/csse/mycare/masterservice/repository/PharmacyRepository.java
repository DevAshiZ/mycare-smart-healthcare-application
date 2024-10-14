package com.csse.mycare.masterservice.repository;

import com.csse.mycare.masterservice.dao.Pharmacy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PharmacyRepository extends JpaRepository<Pharmacy, Integer> {
    boolean getPharmacyByEmail(String email);
}
