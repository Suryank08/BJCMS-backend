package com.bjcms.dao.instructor;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bjcms.entity.instructor.InstructorInfo;

@Repository
public interface InstructorInfoDao extends JpaRepository<InstructorInfo,Integer> {
}
