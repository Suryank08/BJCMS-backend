package com.bjcms.dao.student;

import com.bjcms.entity.student.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentDao extends JpaRepository<Student,Integer> {
    Optional<Student> findByEmail(String email);
}
