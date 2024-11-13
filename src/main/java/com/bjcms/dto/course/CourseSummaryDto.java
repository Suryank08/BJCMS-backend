package com.bjcms.dto.course;

import com.bjcms.dto.instructor.InstructorDto;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import java.util.List;

public class CourseSummaryDto {
    private Integer courseId;
    private String courseImage;
    private String courseName;
    private String courseDuration;
    private String courseCost;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date startDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date endDate;
    private String courseTypeName;
    private String courseStatus;

    public CourseSummaryDto(Integer courseId, String courseImage, String courseName, String courseDuration, String courseCost, Date startDate, Date endDate, String courseTypeName, String courseStatus) {
        this.courseId = courseId;
        this.courseImage = courseImage;
        this.courseName = courseName;
        this.courseDuration = courseDuration;
        this.courseCost = courseCost;
        this.startDate = startDate;
        this.endDate = endDate;
        this.courseTypeName = courseTypeName;
        this.courseStatus = courseStatus;
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

    public String getCourseStatus() {
        return courseStatus;
    }

    public void setCourseStatus(String courseStatus) {
        this.courseStatus = courseStatus;
    }
}
