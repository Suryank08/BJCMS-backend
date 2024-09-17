package com.bjcms.dao.course.online;

import com.bjcms.entity.course.online.OnlineCourseAttendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface OnlineCourseAttendenceDao extends JpaRepository<OnlineCourseAttendance, Integer> {

    @Query("SELECT o FROM OnlineCourseAttendance o WHERE o.onlineCourseId = :onlineCourseId AND o.subjectId = :subjectId AND o.studentId = :studentId")
    Optional<OnlineCourseAttendance> findOnlineCourseAttendence(@Param("onlineCourseId") Integer onlineCourseId,
                                                                @Param("subjectId") Integer subjectId,
                                                                @Param("studentId") Integer studentId);
}


