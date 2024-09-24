package com.bjcms.dto.course;

import com.bjcms.dto.instructor.InstructorDto;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import java.util.List;

public class UserCoursesDto {

    private Integer courseId;

    private String courseImage;

    private String courseName;

    private String courseDuration;

    private String courseCost;

    private String courseDescription;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date startDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date endDate;

    private String courseTypeName;

    private List<InstructorDto> instructorList;

    private OfflineCourseDto offlineCourse;
    private OnlineCourseDto onlineCourse;

    public UserCoursesDto(Integer courseId, String courseImage, String courseName, String courseDuration, String courseCost, String courseDescription, Date startDate, Date endDate, String courseTypeName, List<InstructorDto> instructorList, OfflineCourseDto offlineCourse, OnlineCourseDto onlineCourse) {
        this.courseId = courseId;
        this.courseImage = courseImage;
        this.courseName = courseName;
        this.courseDuration = courseDuration;
        this.courseCost = courseCost;
        this.courseDescription = courseDescription;
        this.startDate = startDate;
        this.endDate = endDate;
        this.courseTypeName = courseTypeName;
        this.instructorList = instructorList;
        this.offlineCourse = offlineCourse;
        this.onlineCourse = onlineCourse;
    }

    public UserCoursesDto() {
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public String getCourseImage() {
        return courseImage;
    }

    public void setCourseImage(String courseImage) {
        this.courseImage = courseImage;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseDuration() {
        return courseDuration;
    }

    public void setCourseDuration(String courseDuration) {
        this.courseDuration = courseDuration;
    }

    public String getCourseCost() {
        return courseCost;
    }

    public void setCourseCost(String courseCost) {
        this.courseCost = courseCost;
    }

    public String getCourseDescription() {
        return courseDescription;
    }

    public void setCourseDescription(String courseDescription) {
        this.courseDescription = courseDescription;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getCourseTypeName() {
        return courseTypeName;
    }

    public void setCourseTypeName(String courseTypeName) {
        this.courseTypeName = courseTypeName;
    }

    public List<InstructorDto> getInstructorList() {
        return instructorList;
    }

    public void setInstructorList(List<InstructorDto> instructorList) {
        this.instructorList = instructorList;
    }

    public OfflineCourseDto getOfflineCourse() {
        return offlineCourse;
    }

    public void setOfflineCourse(OfflineCourseDto offlineCourse) {
        this.offlineCourse = offlineCourse;
    }

    public OnlineCourseDto getOnlineCourse() {
        return onlineCourse;
    }

    public void setOnlineCourse(OnlineCourseDto onlineCourse) {
        this.onlineCourse = onlineCourse;
    }
}
