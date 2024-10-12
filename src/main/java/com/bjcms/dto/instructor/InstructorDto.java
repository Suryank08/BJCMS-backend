package com.bjcms.dto.instructor;

import java.util.List;

public class InstructorDto {

    private Integer instructorId;

    private String instructorName;
    private String instructorInfo;
    private List<String> qualificationList;
    private List<String> SubjectList;
    //FIXME for now we are using String to represent course later replace it with instructorCourseDto
    private List<String> courseList;
    //TODO Later make instructorDtos then return whole course of instructor in frontend to viewd by coaching Admin(feature)
//    private List<CourseDto> instructorCourseDtoList;


    public InstructorDto(Integer instructorId, String instructorName, String instructorInfo, List<String> qualificationList, List<String> subjectList, List<String> courseList) {
        this.instructorId = instructorId;
        this.instructorName = instructorName;
        this.instructorInfo = instructorInfo;
        this.qualificationList = qualificationList;
        SubjectList = subjectList;
        this.courseList = courseList;
    }

    public InstructorDto(Integer instructorId, String instructorName,String instructorInfo) {
        this.instructorId = instructorId;
        this.instructorName = instructorName;
        this.instructorInfo=instructorInfo;
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

    public String getInstructorInfo() {
        return instructorInfo;
    }

    public void setInstructorInfo(String instructorInfo) {
        this.instructorInfo = instructorInfo;
    }

    public List<String> getQualificationList() {
        return qualificationList;
    }

    public void setQualificationList(List<String> qualificationList) {
        this.qualificationList = qualificationList;
    }

    public List<String> getSubjectList() {
        return SubjectList;
    }

    public void setSubjectList(List<String> subjectList) {
        SubjectList = subjectList;
    }

    public List<String> getCourseList() {
        return courseList;
    }

    public void setCourseList(List<String> courseList) {
        this.courseList = courseList;
    }
}
