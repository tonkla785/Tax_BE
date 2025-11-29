package com.pcc.Tax_BE.Repository;

import com.pcc.Tax_BE.Entity.HeaderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface HeaderRepository extends JpaRepository<HeaderEntity,Long> {

    List<HeaderEntity> findByVdtNo(Long vdtNo);

    List<HeaderEntity> findByCreateDate(Date createDate);

    List<HeaderEntity> findByVdtNoAndCreateDate(Long vdtNo, Date createDate);
}
