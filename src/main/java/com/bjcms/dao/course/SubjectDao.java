package com.bjcms.dao.course;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bjcms.entity.course.Subject;

@Repository
public interface SubjectDao extends JpaRepository<Subject,Integer> {
}
