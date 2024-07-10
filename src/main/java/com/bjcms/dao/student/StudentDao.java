package com.bjcms.dao.student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bjcms.entity.student.Student;

@Repository
public interface StudentDao extends JpaRepository<Student,Integer> {
}
