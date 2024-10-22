package com.bjcms.dto.Student;

import java.util.List;

public class StudentSummaryDto {
    private Integer studentId;
    private String studentName;
    private String email;
    private String mobileNumber;

    public StudentSummaryDto(Integer studentId, String studentName, String email, String mobileNumber) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.email = email;
        this.mobileNumber = mobileNumber;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
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
}
