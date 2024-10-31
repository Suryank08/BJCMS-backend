package com.bjcms.dao.course.offline;

import com.bjcms.entity.course.offline.OfflineCourseAttendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OfflineCourseAttendenceDao extends JpaRepository<OfflineCourseAttendance,Integer> {
    @Query("SELECT o FROM OfflineCourseAttendance o WHERE o.courseId = :courseId AND o.subjectId = :subjectId AND o.studentId = :studentId AND o.batchId = :batchId")
    Optional<OfflineCourseAttendance> findOfflineCourseAttendence(@Param("courseId") Integer courseId,
                                                                  @Param("subjectId") Integer subjectId,
                                                                  @Param("studentId") Integer studentId,
                                                                  @Param("batchId") Integer batchId);

}
