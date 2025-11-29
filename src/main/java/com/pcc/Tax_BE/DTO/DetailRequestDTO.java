package com.pcc.Tax_BE.DTO;

import java.math.BigDecimal;
import java.util.Date;

public class DetailRequestDTO {
    private Long id;
    private String bdtNo;
    private String  ndtNo;
    private Date createDate;
    private BigDecimal totalPurchase;
    private BigDecimal taxS;
    private BigDecimal vatBR;
    private BigDecimal vatBA;
    private BigDecimal vatTB;
    private String taxIden;
    private String branchNo;
    private String establishmentName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBdtNo() {
        return bdtNo;
    }

    public void setBdtNo(String bdtNo) {
        this.bdtNo = bdtNo;
    }

    public String  getNdtNo() {
        return ndtNo;
    }

    public void setNdtNo(String  ndtNo) {
        this.ndtNo = ndtNo;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public BigDecimal getTotalPurchase() {
        return totalPurchase;
    }

    public void setTotalPurchase(BigDecimal totalPurchase) {
        this.totalPurchase = totalPurchase;
    }

    public BigDecimal getTaxS() {
        return taxS;
    }

    public void setTaxS(BigDecimal taxS) {
        this.taxS = taxS;
    }

    public BigDecimal getVatBR() {
        return vatBR;
    }

    public void setVatBR(BigDecimal vatBR) {
        this.vatBR = vatBR;
    }

    public BigDecimal getVatBA() {
        return vatBA;
    }

    public void setVatBA(BigDecimal vatBA) {
        this.vatBA = vatBA;
    }

    public BigDecimal getVatTB() {
        return vatTB;
    }

    public void setVatTB(BigDecimal vatTB) {
        this.vatTB = vatTB;
    }

    public String getTaxIden() {
        return taxIden;
    }

    public void setTaxIden(String taxIden) {
        this.taxIden = taxIden;
    }

    public String getBranchNo() {
        return branchNo;
    }

    public void setBranchNo(String branchNo) {
        this.branchNo = branchNo;
    }

    public String getEstablishmentName() {
        return establishmentName;
    }

    public void setEstablishmentName(String establishmentName) {
        this.establishmentName = establishmentName;
    }
}
