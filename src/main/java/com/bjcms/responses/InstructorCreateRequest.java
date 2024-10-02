package com.bjcms.responses;

import com.bjcms.entity.instructor.InstructorInfo;

import java.util.List;

public class InstructorCreateRequest {
   private String instructorName;
    private String email;
   private InstructorInfo instructorInfo;
   private List<Integer> qualificationList;
   private List<Integer> subjectList;
   private String mobileNumber;

    public InstructorCreateRequest(String instructorName, String email, InstructorInfo instructorInfo, List<Integer> qualificationList, List<Integer> subjectList, String mobileNumber) {
        this.instructorName = instructorName;
        this.email = email;
        this.instructorInfo = instructorInfo;
        this.qualificationList = qualificationList;
        this.subjectList = subjectList;
        this.mobileNumber = mobileNumber;
    }

    public InstructorCreateRequest() {
    }

    public String getInstructorName() {
        return instructorName;
    }

    public void setInstructorName(String instructorName) {
        this.instructorName = instructorName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public InstructorInfo getInstructorInfo() {
        return instructorInfo;
    }

    public void setInstructorInfo(InstructorInfo instructorInfo) {
        this.instructorInfo = instructorInfo;
    }

    public List<Integer> getQualificationList() {
        return qualificationList;
    }

    public void setQualificationList(List<Integer> qualificationList) {
        this.qualificationList = qualificationList;
    }

    public List<Integer> getSubjectList() {
        return subjectList;
    }

    public void setSubjectList(List<Integer> subjectList) {
        this.subjectList = subjectList;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }
}
