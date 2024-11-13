package com.bjcms.dto.instructor;

import com.bjcms.dto.Student.StudentDetailDto;
import com.bjcms.dto.course.CourseDto;

import java.util.List;

public class InstructorDetailedDto {
    private InstructorDto instructorDto;
    private List<String> qualificationList;
    private List<String> subjectList;
    private List<CourseDto> courseDtoList;
    private List<StudentDetailDto> studentDtoList;

    public InstructorDetailedDto(InstructorDto instructorDto, List<String> qualificationList, List<String> subjectList, List<CourseDto> courseDtoList, List<StudentDetailDto> studentDtoList) {
        this.instructorDto = instructorDto;
        this.qualificationList = qualificationList;
        this.subjectList = subjectList;
        this.courseDtoList = courseDtoList;
        this.studentDtoList = studentDtoList;
    }

    public InstructorDto getInstructorDto() {
        return instructorDto;
    }

    public void setInstructorDto(InstructorDto instructorDto) {
        this.instructorDto = instructorDto;
    }

    public List<String> getQualificationList() {
        return qualificationList;
    }

    public void setQualificationList(List<String> qualificationList) {
        this.qualificationList = qualificationList;
    }

    public List<String> getSubjectList() {
        return subjectList;
    }

    public void setSubjectList(List<String> subjectList) {
        this.subjectList = subjectList;
    }

    public List<CourseDto> getCourseDtoList() {
        return courseDtoList;
    }

    public void setCourseDtoList(List<CourseDto> courseDtoList) {
        this.courseDtoList = courseDtoList;
    }

    public List<StudentDetailDto> getStudentDtoList() {
        return studentDtoList;
    }

    public void setStudentDtoList(List<StudentDetailDto> studentDtoList) {
        this.studentDtoList = studentDtoList;
    }
}
