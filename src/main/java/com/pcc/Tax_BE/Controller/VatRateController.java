package com.pcc.Tax_BE.Controller;

import com.pcc.Tax_BE.Entity.VatRateEntity;
import com.pcc.Tax_BE.Service.VatRateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("vat-rate-service")
public class VatRateController {

    @Autowired
    private VatRateService vatRateService;

    @GetMapping("/vat-rate")
    public ResponseEntity<?> getAllProduct() {
        List<VatRateEntity> vat = vatRateService.findAll();
        return ResponseEntity.ok(Map.of(
                "responseStatus", 200,
                "responseMessage", "get vat complete",
                "data", vat
        ));
    }
}
