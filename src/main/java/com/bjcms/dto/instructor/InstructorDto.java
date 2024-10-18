package com.bjcms.dto.instructor;

import java.util.List;

public class InstructorDto {

    private Integer instructorId;
    private String instructorName;
    private String instructorEmail;
    private String instructorInfo;


    public InstructorDto(Integer instructorId, String instructorName,String instructorEmail, String instructorInfo) {
        this.instructorId = instructorId;
        this.instructorName = instructorName;
        this.instructorInfo = instructorInfo;
        this.instructorEmail=instructorEmail;

    }

    public InstructorDto() {
    }

    public Integer getInstructorId() {
        return instructorId;
    }

    public void setInstructorId(Integer instructorId) {
        this.instructorId = instructorId;
    }

    public String getInstructorName() {
        return instructorName;
    }

    public void setInstructorName(String instructorName) {
        this.instructorName = instructorName;
    }

    public String getInstructorEmail() {
        return instructorEmail;
    }

    public void setInstructorEmail(String instructorEmail) {
        this.instructorEmail = instructorEmail;
    }

    public String getInstructorInfo() {
        return instructorInfo;
    }

    public void setInstructorInfo(String instructorInfo) {
        this.instructorInfo = instructorInfo;
    }
}
