package com.cms.bjm_cms.dao.instructor;

import com.cms.bjm_cms.entity.instructor.InstructorInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InstructorInfoDao extends JpaRepository<InstructorInfo,Integer> {
}
