package com.bjcms.entity.course.offline;

import java.util.ArrayList;
import java.util.List;

import com.bjcms.entity.student.Student;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;

@Entity
@Table(name = "batch")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "batchId")
public class Batch {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "batch_id")
    private Integer batchId;
    @Column(name = "batch_name")
    private String batchName;
    @Column(name = "batch_time")
    private String batchTime;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private OfflineCourse offlineCourse;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "batch_student_enrollment",joinColumns = @JoinColumn(name = "batch_id"),inverseJoinColumns = @JoinColumn(name = "student_id"))
    private List<Student>studentList=new ArrayList<>();

    public Batch(Integer batchId, String batchName, String batchTime, OfflineCourse offlineCourse, List<Student> studentList) {
        this.batchId = batchId;
        this.batchName = batchName;
        this.batchTime = batchTime;
        this.offlineCourse = offlineCourse;
        this.studentList = studentList;
    }

    public Batch() {
    }

    public Integer getBatchId() {
        return batchId;
    }

    public void setBatchId(Integer batchId) {
        this.batchId = batchId;
    }

    public String getBatchName() {
        return batchName;
    }

    public void setBatchName(String batchName) {
        this.batchName = batchName;
    }

    public String getBatchTime() {
        return batchTime;
    }

    public void setBatchTime(String batchTime) {
        this.batchTime = batchTime;
    }

    public OfflineCourse getOfflineCourse() {
        return offlineCourse;
    }

    public void setOfflineCourse(OfflineCourse offlineCourse) {
        this.offlineCourse = offlineCourse;
    }

    public List<Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<Student> studentList) {
        this.studentList = studentList;
    }
}
