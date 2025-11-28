package com.pcc.Tax_BE.Repository;

import com.pcc.Tax_BE.Entity.HeaderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HeaderRepository extends JpaRepository<HeaderEntity,Long> {
}
