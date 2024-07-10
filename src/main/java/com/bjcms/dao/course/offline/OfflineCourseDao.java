package com.bjcms.dao.course.offline;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bjcms.entity.course.offline.OfflineCourse;

@Repository
public interface OfflineCourseDao extends JpaRepository<OfflineCourse,Integer> {
}
