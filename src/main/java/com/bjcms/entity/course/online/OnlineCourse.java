package com.bjcms.entity.course.online;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.bjcms.entity.course.Course;
import com.bjcms.entity.course.Subject;
import com.bjcms.entity.student.Student;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;

@Entity
@Table(name = "online_course")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "onlineCourseId")
public class OnlineCourse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "online_course_id")
    private Integer onlineCourseId;

    @Column(name = "status", length = 20)
    private String status = "upcoming";

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @OneToOne
    @JoinColumn(name = "course_id")
    private Course course;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "online_course_subject",joinColumns = @JoinColumn(name = "online_course_id"),inverseJoinColumns = @JoinColumn(name="subject_id"))
    List<Subject>subjectList=new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JsonManagedReference
    @JoinTable(name = "online_course_student_enrollment",joinColumns = @JoinColumn(name = "online_course_id"),inverseJoinColumns = @JoinColumn(name="student_id"))
    List<Student>studentList=new ArrayList<>();

    @OneToMany(mappedBy = "onlineCourse")
    List<Video>videoList=new ArrayList<>();

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

    public OnlineCourse(Integer onlineCourseId, String status, LocalDateTime createdAt, LocalDateTime updatedAt, Course course, List<Subject> subjectList, List<Student> studentList, List<Video> videoList) {
        this.onlineCourseId = onlineCourseId;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.course = course;
        this.subjectList = subjectList;
        this.studentList = studentList;
        this.videoList = videoList;
    }

    public OnlineCourse() {
    }

    public Integer getOnlineCourseId() {
        return onlineCourseId;
    }

    public void setOnlineCourseId(Integer courseId) {
        this.onlineCourseId = courseId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public List<Subject> getSubjectList() {
        return subjectList;
    }

    public void setSubjectList(List<Subject> subjectList) {
        this.subjectList = subjectList;
    }

    public List<Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<Student> studentList) {
        this.studentList = studentList;
    }

    public List<Video> getVideoList() {
        return videoList;
    }

    public void setVideoList(List<Video> videoList) {
        this.videoList = videoList;
    }

    @Override
    public String toString() {
        return "OnlineCourse{" +
                "OnlineCourseId=" + onlineCourseId +
                ", status='" + status + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", course=" + course +
                ", subjectList=" + subjectList +
                ", studentList=" + studentList +
                ", videoList=" + videoList +
                '}';
    }
}

