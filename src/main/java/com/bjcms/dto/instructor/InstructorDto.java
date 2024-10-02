package com.bjcms.dto.instructor;

public class InstructorDto {

    private Integer instructorId;

    private String instructorName;


    public InstructorDto(Integer instructorId, String instructorName) {
        this.instructorId = instructorId;
        this.instructorName = instructorName;
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
}
