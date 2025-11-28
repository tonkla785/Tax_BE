package com.pcc.Tax_BE.Controller;

import com.pcc.Tax_BE.DTO.DetailRequestDTO;
import com.pcc.Tax_BE.Entity.HeaderEntity;
import com.pcc.Tax_BE.Service.TaxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("tax-controller")
public class TaxController {
    @Autowired
    private TaxService taxService;

    @PostMapping("/create")
    public ResponseEntity<?> createTax(@RequestBody List<DetailRequestDTO> detailRequestDTOList) {
        try {
            HeaderEntity dataTax = taxService.createTax(detailRequestDTOList);
            return ResponseEntity.ok(Map.of(
                    "responseStatus", 200,
                    "responseMessage", "Create complete",
                    "data", dataTax
            ));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of(
                    "responseStatus", 400,
                    "responseMessage", e.getMessage()
            ));
        } catch (
                Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of(
                    "responseStatus", 500,
                    "responseMessage", e.getMessage()
            ));
        }
    }
}
