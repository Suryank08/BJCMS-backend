package com.bjcms.dao.student;

import com.bjcms.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bjcms.entity.student.Student;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentDao extends JpaRepository<Student,Integer> {
    Optional<Student> findByEmail(String email);
}
