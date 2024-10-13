package com.csse.mycare.masterservice.service;

import com.csse.mycare.masterservice.dao.Pharmacy;
import com.csse.mycare.masterservice.repository.PharmacyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PharmacyServiceImpl implements PharmacyService {
    PharmacyRepository pharmacyRepository;

    @Autowired
    public PharmacyServiceImpl(PharmacyRepository pharmacyRepository) {
        this.pharmacyRepository = pharmacyRepository;
    }

    @Override
    public void savePharmacy(Pharmacy pharmacy) {

    }

    @Override
    public void updatePharmacy(Pharmacy pharmacy) {

    }

    @Override
    public void deletePharmacy(Integer id) {

    }

    @Override
    public void getPharmacyById(Integer id) {

    }
}
