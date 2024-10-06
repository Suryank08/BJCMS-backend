package com.bjcms.dto.StudentDto;
import java.util.List;

public class StudentDto {
    private Integer studentId;
    private String studentName;
    private String email;
    private String mobileNumber;
    private List<String> instructorName;
    private List<String> enrolledCourseList;
    private List<String> batchDetails;

    public StudentDto(Integer studentId, String studentName, String email, String mobileNumber, List<String> instructorName, List<String> enrolledCourseList, List<String> batchDetails) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.email = email;
        this.mobileNumber = mobileNumber;
        this.instructorName = instructorName;
        this.enrolledCourseList = enrolledCourseList;
        this.batchDetails = batchDetails;
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

    public List<String> getInstructorName() {
        return instructorName;
    }

    public void setInstructorName(List<String> instructorName) {
        this.instructorName = instructorName;
    }

    public List<String> getEnrolledCourseList() {
        return enrolledCourseList;
    }

    public void setEnrolledCourseList(List<String> enrolledCourseList) {
        this.enrolledCourseList = enrolledCourseList;
    }

    public List<String> getBatchDetails() {
        return batchDetails;
    }

    public void setBatchDetails(List<String> batchDetails) {
        this.batchDetails = batchDetails;
    }
}
