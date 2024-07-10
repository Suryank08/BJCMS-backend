package com.bjcms.entity.course.offline;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.bjcms.entity.course.Course;
import com.bjcms.entity.course.Subject;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;

@Entity
@Table(name = "offline_course")
public class OfflineCourse {

    @Id
    @Column(name = "course_id")
    private Integer courseId;

    @Column(name = "status", length = 20)
    private String status = "upcoming";

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @OneToOne
    @MapsId
    @JoinColumn(name = "course_id")
    private Course course;

    @ManyToMany
    @JoinTable(name = "offline_course_subject",joinColumns = @JoinColumn(name = "course_id"),inverseJoinColumns = @JoinColumn(name="subject_id"))
    private List<Subject> subjectList=new ArrayList<>();

    @OneToMany(mappedBy = "offlineCourse")
    private List<Batch>batchList=new ArrayList<>();

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

    public OfflineCourse(Integer courseId, String status, LocalDateTime createdAt, LocalDateTime updatedAt, Course course, List<Subject> subjectList, List<Batch> batchList) {
        this.courseId = courseId;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.course = course;
        this.subjectList = subjectList;
        this.batchList = batchList;
    }

    public OfflineCourse() {
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
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

    public List<Batch> getBatchList() {
        return batchList;
    }

    public void setBatchList(List<Batch> batchList) {
        this.batchList = batchList;
    }
}

