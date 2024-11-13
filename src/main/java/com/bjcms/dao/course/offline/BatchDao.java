package com.bjcms.dao.course.offline;

import com.bjcms.entity.course.offline.Batch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BatchDao extends JpaRepository<Batch,Integer> {
}
