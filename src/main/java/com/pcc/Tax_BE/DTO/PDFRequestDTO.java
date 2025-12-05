package com.pcc.Tax_BE.DTO;

import com.pcc.Tax_BE.Entity.DetailEntity;

import java.util.List;

public class PDFRequestDTO {
    private Long vdtNo;
    private String  vdtDate;
    private List<DetailEntity> detailEntityList;

    public String getVdtDate() {
        return vdtDate;
    }

    public void setVdtDate(String vdtDate) {
        this.vdtDate = vdtDate;
    }

    public Long getVdtNo() {
        return vdtNo;
    }

    public void setVdtNo(Long vdtNo) {
        this.vdtNo = vdtNo;
    }


    public List<DetailEntity> getDetailEntityList() {
        return detailEntityList;
    }

    public void setDetailEntityList(List<DetailEntity> detailEntityList) {
        this.detailEntityList = detailEntityList;
    }
}
