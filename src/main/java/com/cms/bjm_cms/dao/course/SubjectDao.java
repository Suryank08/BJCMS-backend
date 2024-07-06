package com.cms.bjm_cms.dao.course;

import com.cms.bjm_cms.entity.course.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubjectDao extends JpaRepository<Subject,Integer> {
}
