package com.bjcms.dao.instructor;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bjcms.entity.instructor.Instructor;

@Repository
public interface InstructorDao extends JpaRepository<Instructor,Integer> {
}
