package com.bjcms.dao.course.online;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bjcms.entity.course.online.OnlineCourse;

@Repository
public interface OnlineCourseDao extends JpaRepository<OnlineCourse,Integer> {
}
