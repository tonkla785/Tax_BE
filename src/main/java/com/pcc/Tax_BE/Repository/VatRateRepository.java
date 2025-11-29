package com.pcc.Tax_BE.Repository;

import com.pcc.Tax_BE.Entity.VatRateEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;

public interface VatRateRepository extends JpaRepository<VatRateEntity, BigDecimal> {
}
