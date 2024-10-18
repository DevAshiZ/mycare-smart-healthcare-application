package com.csse.mycare.masterservice.service;

import com.csse.mycare.admin.dto.PharmacyRegistrationRequest;
import com.csse.mycare.common.exceptions.ReferedPharmacyNotFoundException;
import com.csse.mycare.masterservice.dao.Pharmacy;
import com.csse.mycare.masterservice.repository.PharmacyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public Pharmacy updatePharmacy(PharmacyRegistrationRequest pharmacy) throws ReferedPharmacyNotFoundException {
        try {
            Pharmacy existingPharmacy = pharmacyRepository.getReferenceById(pharmacy.getPharmacyId());
            if (pharmacy.getPharmacyName() != null) {
                existingPharmacy.setPharmacyName(pharmacy.getPharmacyName());
            }
            if (pharmacy.getPharmacyAddress() != null) {
                existingPharmacy.setPharmacyAddress(pharmacy.getPharmacyAddress());
            }
            if (pharmacy.getEmail() != null) {
                existingPharmacy.setEmail(pharmacy.getEmail());
            }
            if (pharmacy.getFirstName() != null) {
                existingPharmacy.setFirstName(pharmacy.getFirstName());
            }
            if (pharmacy.getLastName() != null) {
                existingPharmacy.setLastName(pharmacy.getLastName());
            }
            return pharmacyRepository.save(existingPharmacy);
        } catch (Exception e) {
            throw new ReferedPharmacyNotFoundException();
        }
    }

    @Override
    public void deletePharmacy(Integer id) {
        pharmacyRepository.deleteById(id);
    }

    @Override
    public Pharmacy getPharmacyById(Integer id) {
        return pharmacyRepository.getReferenceById(id);
    }

    @Override
    public List<Pharmacy> getAllPharmacies() {
        return pharmacyRepository.findAll();
    }
}
