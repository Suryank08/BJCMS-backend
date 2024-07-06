package com.cms.bjm_cms.dao.course.online;

import com.cms.bjm_cms.entity.course.online.OnlineCourse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OnlineCourseDao extends JpaRepository<OnlineCourse,Integer> {
}
