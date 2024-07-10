package com.bjcms.entity.student;

import java.util.ArrayList;
import java.util.List;

import com.bjcms.entity.course.offline.Batch;
import com.bjcms.entity.course.online.Comment;
import com.bjcms.entity.course.online.OnlineCourse;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "student")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_id")
    private int studentId;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "email")
    private String email;
    @Column(name = "mobile_number")
    private String mobileNumber;

    @ManyToMany
    @JoinTable(name = "online_course_student_enrollment", joinColumns = @JoinColumn(name = "student_id"), inverseJoinColumns = @JoinColumn(name = "course_id"))
    private List<OnlineCourse> onlineCourseList = new ArrayList<>();

    @OneToMany(mappedBy = "student")
    List<Comment> commentList = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "batch_student_enrollment", joinColumns = @JoinColumn(name = "student_id"), inverseJoinColumns = @JoinColumn(name = "batch_id"))
    List<Batch> batchList = new ArrayList<>();

    public Student(int studentId, String firstName, String lastName, String email, String mobileNumber, List<OnlineCourse> onlineCourseList, List<Comment> commentList, List<Batch> batchList) {
        this.studentId = studentId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.mobileNumber = mobileNumber;
        this.onlineCourseList = onlineCourseList;
        this.commentList = commentList;
        this.batchList = batchList;
    }

    public Student() {
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public List<OnlineCourse> getOnlineCourseList() {
        return onlineCourseList;
    }

    public void setOnlineCourseList(List<OnlineCourse> onlineCourseList) {
        this.onlineCourseList = onlineCourseList;
    }

    public List<Comment> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<Comment> commentList) {
        this.commentList = commentList;
    }

    public List<Batch> getBatchList() {
        return batchList;
    }

    public void setBatchList(List<Batch> batchList) {
        this.batchList = batchList;
    }
}