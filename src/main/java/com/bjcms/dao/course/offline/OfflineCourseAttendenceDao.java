package com.bjcms.dao.course.offline;

import com.bjcms.entity.course.offline.OfflineCourseAttendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OfflineCourseAttendenceDao extends JpaRepository<OfflineCourseAttendance,Integer> {
}
