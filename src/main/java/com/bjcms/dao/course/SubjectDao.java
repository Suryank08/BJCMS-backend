package com.bjcms.dao.course;

import com.bjcms.entity.course.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubjectDao extends JpaRepository<Subject,Integer> {
}
