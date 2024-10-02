
package com.bjcms.entity.course;

import com.bjcms.entity.course.online.OnlineCourseAttendance;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "date_table")
public class AttendanceDateEntry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "date_id")
    private Integer dateId;

    @Column(name = "attendance_date")
    private LocalDate attendanceDate;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "date_online_course_attendance",joinColumns = @JoinColumn(name = "date_id"),inverseJoinColumns = @JoinColumn(name = "attendance_id"))
    private List<OnlineCourseAttendance> onlineCourseAttendanceList =new ArrayList<>();

    public AttendanceDateEntry(LocalDate attendanceDate) {
        this.attendanceDate = attendanceDate;
    }

    public AttendanceDateEntry() {
    }
    // Getters and Setters

    public Integer getDateId() {
        return dateId;
    }

    public void setDateId(Integer dateId) {
        this.dateId = dateId;
    }

    public LocalDate getAttendanceDate() {
        return attendanceDate;
    }

    public void setAttendanceDate(LocalDate attendanceDate) {
        this.attendanceDate = attendanceDate;
    }

    public List<OnlineCourseAttendance> getOnlineCourseAttendanceList() {
        return onlineCourseAttendanceList;
    }

    public void setOnlineCourseAttendanceList(List<OnlineCourseAttendance> onlineCourseAttendanceList) {
        this.onlineCourseAttendanceList = onlineCourseAttendanceList;
    }
}
