package com.pcc.Tax_BE.Repository;

import com.pcc.Tax_BE.Entity.DetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetailRepository extends JpaRepository<DetailEntity,Long> {
}
