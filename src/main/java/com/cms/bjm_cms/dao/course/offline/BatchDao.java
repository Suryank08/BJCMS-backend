package com.cms.bjm_cms.dao.course.offline;

import com.cms.bjm_cms.entity.course.offline.Batch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BatchDao extends JpaRepository<Batch,Integer> {
}
