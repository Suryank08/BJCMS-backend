package com.bjcms.dao.course;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bjcms.entity.course.CourseType;

@Repository
public interface CourseTypeDao extends JpaRepository<CourseType,Integer> {
}
