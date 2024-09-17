package com.bjcms.entity.course.online;

import com.bjcms.entity.course.AttendanceDateEntry;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "online_course_attendance_record")
public class OnlineCourseAttendance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "attendance_id")
    private Integer attendanceId;

    @Column(name = "online_course_id")
    private Integer onlineCourseId;

    @Column(name = "subject_id")
    private Integer subjectId;
    @Column(name = "student_id")
    private Integer studentId;
    @Column(name = "attendance_count")
    private Integer attendanceCount;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "date_online_course_attendance",joinColumns = @JoinColumn(name = "attendance_id"),inverseJoinColumns = @JoinColumn(name = "date_id"))
    private List<AttendanceDateEntry> attendanceDateEntryList =new ArrayList<>();

    public OnlineCourseAttendance() {
    }

    public OnlineCourseAttendance(Integer attendanceId, Integer onlineCourseId, Integer subjectId, Integer studentId, Integer attendanceCount, List<AttendanceDateEntry> attendanceDateEntryList) {
        this.attendanceId = attendanceId;
        this.onlineCourseId = onlineCourseId;
        this.subjectId = subjectId;
        this.studentId = studentId;
        this.attendanceCount = attendanceCount;
        this.attendanceDateEntryList = attendanceDateEntryList;
    }

    public Integer getAttendanceId() {
        return attendanceId;
    }

    public void setAttendanceId(Integer attendanceId) {
        this.attendanceId = attendanceId;
    }

    public Integer getOnlineCourseId() {
        return onlineCourseId;
    }

    public void setOnlineCourseId(Integer onlineCourseId) {
        this.onlineCourseId = onlineCourseId;
    }

    public Integer getAttendanceCount() {
        return attendanceCount;
    }

    public void setAttendanceCount(Integer attendanceCount) {
        this.attendanceCount = attendanceCount;
    }

    public List<AttendanceDateEntry> getAttendanceDateList() {
        return attendanceDateEntryList;
    }

    public void setAttendanceDateList(List<AttendanceDateEntry> attendanceDateEntryList) {
        this.attendanceDateEntryList = attendanceDateEntryList;
    }

    public Integer getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Integer subjectId) {
        this.subjectId = subjectId;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public Integer getAttendenceCount() {
        return attendanceCount;
    }

    public void setAttendenceCount(Integer attendanceCount) {
        this.attendanceCount = attendanceCount;
    }
}
