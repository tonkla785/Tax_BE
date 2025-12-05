package com.pcc.Tax_BE.Controller;

import com.pcc.Tax_BE.DTO.DetailRequestDTO;
import com.pcc.Tax_BE.DTO.PDFRequestDTO;
import com.pcc.Tax_BE.Entity.HeaderEntity;
import com.pcc.Tax_BE.Service.TaxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

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

    @PostMapping("/pdf")
    public ResponseEntity<byte[]> generatePdf(@RequestBody PDFRequestDTO header) {
        try {
            byte[] pdfBytes = taxService.generatePdf(header);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.set("Content-Disposition", "inline; filename=report.pdf");

            return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }



}
