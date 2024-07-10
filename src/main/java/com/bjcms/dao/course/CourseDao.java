package com.bjcms.dao.course;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bjcms.entity.course.Course;

@Repository
public interface CourseDao extends JpaRepository<Course,Integer> {
}
