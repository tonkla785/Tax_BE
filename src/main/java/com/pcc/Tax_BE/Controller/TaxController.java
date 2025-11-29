package com.pcc.Tax_BE.Controller;

import com.pcc.Tax_BE.DTO.DetailRequestDTO;
import com.pcc.Tax_BE.Entity.HeaderEntity;
import com.pcc.Tax_BE.Service.TaxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("tax-service")
public class TaxController {

    @Autowired
    private TaxService taxService;

    @PostMapping("/create")
    public ResponseEntity<?> createTax(@RequestBody List<DetailRequestDTO> detailRequestDTOList) {
        HeaderEntity dataTax = taxService.createTax(detailRequestDTOList);
        return ResponseEntity.ok(Map.of(
                "responseStatus", 200,
                "responseMessage", "Create complete",
                "data", dataTax
        ));
    }

    @PutMapping("/update/{headerId}")
    public ResponseEntity<?> updateTax(@PathVariable Long headerId, @RequestBody List<DetailRequestDTO> detailRequestDTO) {
        HeaderEntity updatedHeader = taxService.updateTax(headerId, detailRequestDTO);
        return ResponseEntity.ok(Map.of(
                "responseStatus", 200,
                "responseMessage", "Update complete",
                "data", updatedHeader
        ));
    }

    @GetMapping("/search")
    public ResponseEntity<?> searchHeader(
            @RequestParam(required = false) Long vdtNo,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date createDate
    ) {
        List<HeaderEntity> data = taxService.searchHeader(vdtNo, createDate);
        return ResponseEntity.ok(Map.of(
                "responseStatus", 200,
                "responseMessage", "Search complete",
                "data", data
        ));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteDetail(@PathVariable Long id) {
        taxService.deleteDetail(id);
        return ResponseEntity.ok(Map.of(
                "responseStatus", 200,
                "responseMessage", "Delete Detail complete"
        ));
    }
}
