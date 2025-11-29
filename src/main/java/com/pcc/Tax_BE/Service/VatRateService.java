package com.pcc.Tax_BE.Service;

import com.pcc.Tax_BE.Entity.VatRateEntity;
import com.pcc.Tax_BE.Repository.VatRateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VatRateService {

    @Autowired
    private VatRateRepository vatRateRepository;

    //get vat rate
    public List<VatRateEntity> findAll(){
        try{
            return vatRateRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Error while Get VatRate",e);
        }
    }
}
