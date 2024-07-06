package com.cms.bjm_cms.dao.instructor;

import com.cms.bjm_cms.entity.instructor.Instructor;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface InstructorDao extends JpaRepository<Instructor,Integer> {
}
