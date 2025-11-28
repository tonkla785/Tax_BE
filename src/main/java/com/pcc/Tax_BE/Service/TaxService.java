package com.pcc.Tax_BE.Service;

import com.pcc.Tax_BE.DTO.DetailRequestDTO;
import com.pcc.Tax_BE.Entity.DetailEntity;
import com.pcc.Tax_BE.Entity.HeaderEntity;
import com.pcc.Tax_BE.Repository.HeaderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class TaxService {
    @Autowired
    private HeaderRepository headerRepository;

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

    public void validate(DetailRequestDTO detailRequestDTO) {
        if (ObjectUtils.isEmpty(detailRequestDTO.getBdtNo()))
            throw new IllegalArgumentException("bdtNo ไม่สามารถเป็น null หรือว่างได้");
        if (ObjectUtils.isEmpty(detailRequestDTO.getTaxIden()))
            throw new IllegalArgumentException("taxIden ไม่สามารถเป็น null หรือว่างได้");
        if (ObjectUtils.isEmpty(detailRequestDTO.getBranchNo()))
            throw new IllegalArgumentException("branchNo ไม่สามารถเป็น null หรือว่างได้");
        if (ObjectUtils.isEmpty(detailRequestDTO.getEstablishmentName()))
            throw new IllegalArgumentException("establishmentName ไม่สามารถเป็น null หรือว่างได้");
        if (detailRequestDTO.getNdtNo() == null)
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
