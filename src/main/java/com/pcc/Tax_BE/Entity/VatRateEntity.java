package com.pcc.Tax_BE.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.math.BigDecimal;

@Entity
@Table(name = "rdbvrtrate")
public class VatRateEntity {

    @Id
    @Column(name = "sale_from", precision = 31, scale = 2)
    private BigDecimal saleFrom;

    @Column(name = "sale_to", precision = 31, scale = 2)
    private BigDecimal saleTo;

    @Column(name = "vrt_rate", precision = 31, scale = 2)
    private BigDecimal vrtRate;

    @Column(name = "vrt_rate_ag", precision = 31, scale = 2)
    private BigDecimal vrtRateAg;

    public BigDecimal getSaleFrom() {
        return saleFrom;
    }

    public void setSaleFrom(BigDecimal saleFrom) {
        this.saleFrom = saleFrom;
    }

    public BigDecimal getSaleTo() {
        return saleTo;
    }

    public void setSaleTo(BigDecimal saleTo) {
        this.saleTo = saleTo;
    }

    public BigDecimal getVrtRate() {
        return vrtRate;
    }

    public void setVrtRate(BigDecimal vrtRate) {
        this.vrtRate = vrtRate;
    }

    public BigDecimal getVrtRateAg() {
        return vrtRateAg;
    }

    public void setVrtRateAg(BigDecimal vrtRateAg) {
        this.vrtRateAg = vrtRateAg;
    }
}
