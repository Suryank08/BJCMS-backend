package com.bjcms.dao.instructor;

import com.bjcms.entity.instructor.InstructorInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InstructorInfoDao extends JpaRepository<InstructorInfo,Integer> {
}
