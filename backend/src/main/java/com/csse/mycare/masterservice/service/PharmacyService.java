package com.csse.mycare.masterservice.service;

import com.csse.mycare.masterservice.dao.Pharmacy;

public interface PharmacyService {
    public void savePharmacy(Pharmacy pharmacy);
    public void updatePharmacy(Pharmacy pharmacy);
    public void deletePharmacy(Integer id);
    public Pharmacy getPharmacyById(Integer id);
}
