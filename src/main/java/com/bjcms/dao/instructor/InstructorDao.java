package com.bjcms.dao.instructor;

import com.bjcms.entity.student.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bjcms.entity.instructor.Instructor;

import java.util.Optional;

@Repository
public interface InstructorDao extends JpaRepository<Instructor,Integer> {
    Optional<Instructor> findByEmail(String email);
}
