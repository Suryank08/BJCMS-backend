package com.bjcms.dto.instructor;

import com.bjcms.entity.course.Course;
import com.bjcms.entity.course.Subject;
import com.bjcms.entity.instructor.InstructorInfo;
import com.bjcms.entity.instructor.Qualification;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

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
