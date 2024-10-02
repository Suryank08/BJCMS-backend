package com.bjcms.dao.course.offline;

import com.bjcms.entity.course.offline.OfflineCourse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OfflineCourseDao extends JpaRepository<OfflineCourse,Integer> {
}
