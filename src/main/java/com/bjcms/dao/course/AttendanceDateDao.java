package com.bjcms.dao.course;

import com.bjcms.entity.course.AttendanceDateEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;
@Repository
public interface AttendanceDateDao extends JpaRepository<AttendanceDateEntry,Integer> {
    @Query("SELECT dateEntry FROM AttendanceDateEntry dateEntry WHERE dateEntry.attendanceDate = :date")
    public Optional<AttendanceDateEntry> findByDate(@Param("date") LocalDate date);

}
