package com.pcc.Tax_BE.Entity;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "header_tax")
public class HeaderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vdt_no")
    private Long vdtNo;

    @Column(name = "vdt_date")
    @Temporal(TemporalType.DATE)
    private Date vdtDate;

    @Column(name = "create_by")
    private String createBy;

    @Column(name = "create_date")
    @Temporal(TemporalType.DATE)
    private Date createDate;

    @OneToMany(mappedBy = "header", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<DetailEntity> detailEntityList;

    public Long  getVdtNo() {
        return vdtNo;
    }

    public void setVdtNo(Long  vdtNo) {
        this.vdtNo = vdtNo;
    }

    public Date getVdtDate() {
        return vdtDate;
    }

    public void setVdtDate(Date vdtDate) {
        this.vdtDate = vdtDate;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public List<DetailEntity> getDetailEntityList() {
        return detailEntityList;
    }

    public void setDetailEntityList(List<DetailEntity> detailEntityList) {
        this.detailEntityList = detailEntityList;
    }
}
