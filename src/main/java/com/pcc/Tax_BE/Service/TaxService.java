package com.pcc.Tax_BE.Service;

import com.pcc.Tax_BE.DTO.DetailRequestDTO;
import com.pcc.Tax_BE.DTO.PDFRequestDTO;
import com.pcc.Tax_BE.Entity.DetailEntity;
import com.pcc.Tax_BE.Entity.HeaderEntity;
import com.pcc.Tax_BE.Repository.DetailRepository;
import com.pcc.Tax_BE.Repository.HeaderRepository;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.io.InputStream;
import java.util.*;

@Service
public class TaxService {
    @Autowired
    private HeaderRepository headerRepository;
    @Autowired
    private DetailRepository detailRepository;

    //create
    @Transactional
    public HeaderEntity createTax(List<DetailRequestDTO> detailRequestDTO) {
        try {
            HeaderEntity info = new HeaderEntity();
            info.setVdtDate(new Date());
            info.setCreateBy("Tonkla");
            info.setCreateDate(new Date());

            List<DetailEntity> detailList = new ArrayList<>();

            if (!ObjectUtils.isEmpty(detailRequestDTO)) {
                for (DetailRequestDTO dto : detailRequestDTO) {
                    DetailEntity detail = new DetailEntity();
                    validate(dto);
                    detail.setBdtNo(dto.getBdtNo());
                    detail.setNdtNo(dto.getNdtNo());
                    detail.setCreateDate(dto.getCreateDate());
                    detail.setCreateBy(info.getCreateBy());
                    detail.setUpdateBy(info.getCreateBy());
                    detail.setUpdateDate(new Date());
                    detail.setTotalPurchase(dto.getTotalPurchase());
                    detail.setTaxS(dto.getTaxS());
                    detail.setVatBR(dto.getVatBR());
                    detail.setVatBA(dto.getVatBA());
                    detail.setVatTB(dto.getVatTB());
                    detail.setTaxIden(dto.getTaxIden());
                    detail.setBranchNo(dto.getBranchNo());
                    detail.setEstablishmentName(dto.getEstablishmentName());

                    detail.setHeader(info);
                    detailList.add(detail);
                }
            }

            info.setDetailEntityList(detailList);
            return headerRepository.save(info);
        } catch (IllegalArgumentException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Error while Saving", e);
        }
    }

    //update
    @Transactional
    public HeaderEntity updateTax(Long headerId, List<DetailRequestDTO> detailRequestDTO) {
        try {
            HeaderEntity existingHeader = headerRepository.findById(headerId)
                    .orElseThrow(() -> new IllegalArgumentException("ไม่พบข้อมูล headerId = " + headerId));

            for (DetailRequestDTO dto : detailRequestDTO) {
                validate(dto);

                DetailEntity detail;

                if (dto.getId() != null && dto.getId() != 0) {
                    detail = detailRepository.findById(dto.getId())
                            .orElseThrow(() -> new IllegalArgumentException("ไม่พบ detailId = " + dto.getId()));
                } else {
                    detail = new DetailEntity();
                    detail.setHeader(existingHeader);
                    existingHeader.getDetailEntityList().add(detail);
                }

                if (!ObjectUtils.isEmpty(dto.getBdtNo())) detail.setBdtNo(dto.getBdtNo());
                if (!ObjectUtils.isEmpty(dto.getNdtNo())) detail.setNdtNo(dto.getNdtNo());
                if (dto.getTotalPurchase() != null) detail.setTotalPurchase(dto.getTotalPurchase());
                if (dto.getTaxS() != null) detail.setTaxS(dto.getTaxS());
                if (dto.getVatBR() != null) detail.setVatBR(dto.getVatBR());
                if (dto.getVatBA() != null) detail.setVatBA(dto.getVatBA());
                if (dto.getVatTB() != null) detail.setVatTB(dto.getVatTB());
                if (!ObjectUtils.isEmpty(dto.getTaxIden())) detail.setTaxIden(dto.getTaxIden());
                if (!ObjectUtils.isEmpty(dto.getBranchNo())) detail.setBranchNo(dto.getBranchNo());
                if (!ObjectUtils.isEmpty(dto.getEstablishmentName()))
                    detail.setEstablishmentName(dto.getEstablishmentName());
                if (!ObjectUtils.isEmpty(dto.getCreateDate())) detail.setCreateDate(dto.getCreateDate());

                detail.setUpdateBy(existingHeader.getCreateBy());
                detail.setCreateBy(existingHeader.getCreateBy());
                detail.setUpdateDate(new Date());
            }

            return headerRepository.save(existingHeader);
        } catch (IllegalArgumentException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Error while Updating", e);
        }
    }

    //search
    @Transactional
    public List<HeaderEntity> searchHeader(Long vdtNo, Date createDate) {
        try {
            List<HeaderEntity> result;
            if ((vdtNo == null || vdtNo == 0) && ObjectUtils.isEmpty(createDate)) {
                throw new IllegalArgumentException("Please Enter field");
            }

            if (vdtNo != null && !ObjectUtils.isEmpty(createDate) && vdtNo != 0) {
                result = headerRepository.findByVdtNoAndCreateDate(vdtNo, createDate);
            } else if (vdtNo != null && vdtNo != 0) {
                result = headerRepository.findByVdtNo(vdtNo);
            } else {
                result = headerRepository.findByCreateDate(createDate);
            }

            if (result.isEmpty()) {
                throw new NoSuchElementException("Not found");
            }

            return result;
        } catch (NoSuchElementException | IllegalArgumentException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Error while Searching", e);
        }
    }

    //delete detail
    @Transactional
    public void deleteDetail(Long detailId) {
        try {
            DetailEntity detail = detailRepository.findById(detailId)
                    .orElseThrow(() -> new IllegalArgumentException("ไม่พบ detailId = " + detailId));

            HeaderEntity header = detail.getHeader();
            if (!ObjectUtils.isEmpty(header)) {
                header.getDetailEntityList().remove(detail);
            }
            detailRepository.delete(detail);
        } catch (IllegalArgumentException e) {
            throw e;
        } catch (RuntimeException e) {
            throw new RuntimeException("Error while Deleting", e);
        }
    }

    @Transactional
    public byte[] generatePdf(PDFRequestDTO header) {
        try {
            Map<String, Object> params = new HashMap<>();
            params.put("vdtNo", header.getVdtNo());
            params.put("vdtDate", header.getVdtDate());
            params.put("printDate", new Date());

            JRBeanCollectionDataSource dataSource =
                    new JRBeanCollectionDataSource(header.getDetailEntityList());

            InputStream reportInput = getClass().getClassLoader()
                    .getResourceAsStream("report/Tax_report.jrxml");

            JasperReport jasperReport = JasperCompileManager.compileReport(reportInput);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, dataSource);

            return JasperExportManager.exportReportToPdf(jasperPrint);

        } catch (Exception e) {
            throw new RuntimeException("Error generating PDF", e);
        }
    }


    public void validate(DetailRequestDTO detailRequestDTO) {
        if (ObjectUtils.isEmpty(detailRequestDTO.getBdtNo()))
            throw new IllegalArgumentException("bdtNo ไม่สามารถเป็น null หรือว่างได้");
        if (ObjectUtils.isEmpty(detailRequestDTO.getTaxIden()))
            throw new IllegalArgumentException("taxIden ไม่สามารถเป็น null หรือว่างได้");
        if (ObjectUtils.isEmpty(detailRequestDTO.getBranchNo()))
            throw new IllegalArgumentException("branchNo ไม่สามารถเป็น null หรือว่างได้");
        if (ObjectUtils.isEmpty(detailRequestDTO.getEstablishmentName()))
            throw new IllegalArgumentException("establishmentName ไม่สามารถเป็น null หรือว่างได้");
        if (ObjectUtils.isEmpty(detailRequestDTO.getNdtNo()))
            throw new IllegalArgumentException("ndtNo ไม่สามารถเป็น null ได้");
        if (detailRequestDTO.getTotalPurchase() == null)
            throw new IllegalArgumentException("totalPurchase ไม่สามารถเป็น null ได้");
        if (detailRequestDTO.getTaxS() == null)
            throw new IllegalArgumentException("taxS ไม่สามารถเป็น null ได้");
        if (detailRequestDTO.getVatBR() == null)
            throw new IllegalArgumentException("vatBR ไม่สามารถเป็น null ได้");
        if (detailRequestDTO.getVatBA() == null)
            throw new IllegalArgumentException("vatBA ไม่สามารถเป็น null ได้");
        if (detailRequestDTO.getVatTB() == null)
            throw new IllegalArgumentException("vatTB ไม่สามารถเป็น null ได้");
        if (ObjectUtils.isEmpty(detailRequestDTO.getCreateDate()))
            throw new IllegalArgumentException("createDate ไม่สามารถเป็น null ได้");
    }

}
