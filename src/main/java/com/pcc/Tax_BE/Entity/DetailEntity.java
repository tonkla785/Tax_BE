package com.pcc.Tax_BE.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "detail_tax")
public class DetailEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_detail")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "vdt_no")
    @JsonIgnore
    private HeaderEntity header;

    @Column(name = "bdt_no")
    private String bdtNo;

    @Column(name = "ndt_no")
    private String  ndtNo;

    @Column(name = "create_date")
    @Temporal(TemporalType.DATE)
    private Date createDate;

    @Column(name = "create_by")
    private String createBy;

    @Column(name = "update_by")
    private String updateBy;

    @Column(name = "update_date")
    @Temporal(TemporalType.DATE)
    private Date updateDate;

    @Column(name = "total_purchase", precision = 31, scale = 2)
    private BigDecimal totalPurchase;

    @Column(name = "tax", precision = 31, scale = 2)
    private BigDecimal taxS;

    @Column(name = "vat_back_revenue", precision = 31, scale = 2)
    private BigDecimal vatBR;

    @Column(name = "vat_back_agent", precision = 31, scale = 2)
    private BigDecimal vatBA;

    @Column(name = "vat_total_back", precision = 31, scale = 2)
    private BigDecimal vatTB;

    @Column(name = "branch")
    private String branchNo;

    @Column(name = "establishment")
    private String establishmentName;

    @Column(name = "tax_identification")
    private String taxIden;

    public String getTaxIden() {
        return taxIden;
    }

    public void setTaxIden(String taxIden) {
        this.taxIden = taxIden;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public HeaderEntity getHeader() {
        return header;
    }

    public void setHeader(HeaderEntity header) {
        this.header = header;
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

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
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
