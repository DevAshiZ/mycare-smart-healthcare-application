package com.csse.mycare.masterservice.service;

import com.csse.mycare.masterservice.dao.Medicine;

import java.util.List;

public interface MedicineService {
    Medicine saveMedicine(Medicine medicine);

    Medicine getMedicine(Integer id);

    Medicine updateMedicine(Medicine medicine);

    void deleteMedicine(Integer id);

    List<Medicine> getAllMedicines();
}
