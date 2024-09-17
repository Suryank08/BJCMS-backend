package com.bjcms.entity.course.offline;

import jakarta.persistence.*;

@Entity
@Table(name = "online_course_attendance_record")
public class OfflineCourseAttendance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "attendance_id")
    private Integer attendanceId;

    @Column(name = "course_id")
    private Integer courseId;
    @Column(name = "batch_id")
    private Integer batchId;
    @Column(name = "subject_id")
    private Integer subjectId;
    @Column(name = "student_id")
    private Integer studentId;
    @Column(name = "attendance_count")
    private Integer attendanceCount;

    public OfflineCourseAttendance() {
    }

    public OfflineCourseAttendance(Integer attendanceId, Integer courseId, Integer batchId, Integer subjectId, Integer studentId, Integer attendanceCount) {
        this.attendanceId = attendanceId;
        this.courseId = courseId;
        this.batchId = batchId;
        this.subjectId = subjectId;
        this.studentId = studentId;
        this.attendanceCount = attendanceCount;
    }

    public Integer getAttendanceId() {
        return attendanceId;
    }

    public void setAttendanceId(Integer attendanceId) {
        this.attendanceId = attendanceId;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public Integer getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Integer subjectId) {
        this.subjectId = subjectId;
    }

    public Integer getBatchId() {
        return batchId;
    }

    public void setBatchId(Integer batchId) {
        this.batchId = batchId;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public Integer getAttendanceCount() {
        return attendanceCount;
    }

    public void setAttendanceCount(Integer attendanceCount) {
        this.attendanceCount = attendanceCount;
    }
}
