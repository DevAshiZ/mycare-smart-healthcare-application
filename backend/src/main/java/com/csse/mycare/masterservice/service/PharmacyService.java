package com.csse.mycare.masterservice.service;

import com.csse.mycare.admin.dto.PharmacyRegistrationRequest;
import com.csse.mycare.common.exceptions.ReferedPharmacyNotFoundException;
import com.csse.mycare.masterservice.dao.Pharmacy;

import java.util.List;

public interface PharmacyService {
    public void savePharmacy(Pharmacy pharmacy);
    public Pharmacy updatePharmacy(PharmacyRegistrationRequest pharmacy) throws ReferedPharmacyNotFoundException;
    public void deletePharmacy(Integer id);
    public Pharmacy getPharmacyById(Integer id);
    public List<Pharmacy> getAllPharmacies();
}
