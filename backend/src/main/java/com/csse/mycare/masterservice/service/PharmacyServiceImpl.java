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
        pharmacyRepository.save(pharmacy);
    }

    @Override
    public void updatePharmacy(Pharmacy pharmacy) {

    }

    @Override
    public void deletePharmacy(Integer id) {

    }

    @Override
    public Pharmacy getPharmacyById(Integer id) {
        return pharmacyRepository.getReferenceById(id);
    }
}
