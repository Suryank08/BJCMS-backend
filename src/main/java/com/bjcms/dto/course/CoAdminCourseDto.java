package com.bjcms.dto.course;

import com.bjcms.dto.instructor.InstructorDto;
import com.bjcms.entity.coaching.Coaching;
import com.bjcms.entity.course.CourseType;
import com.bjcms.entity.course.offline.OfflineCourse;
import com.bjcms.entity.course.online.OnlineCourse;
import com.bjcms.entity.instructor.Instructor;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CoAdminCourseDto {
    private Integer courseId;
    private String courseImage;
    private String courseName;
    private String courseDuration;
    private String courseCost;
    private String courseDescription;
    private Date startDate;
    private Date endDate;
    private String courseTypeName;
    private OnlineCourseDto onlineCourseDto;
    private OfflineCourseDto offlineCourseDto;
    private List<InstructorDto> instructorDtoList;

    public CoAdminCourseDto(Integer courseId, String courseImage, String courseName, String courseDuration, String courseCost, String courseDescription, Date startDate, Date endDate, String courseTypeName, OnlineCourseDto onlineCourseDto, OfflineCourseDto offlineCourseDto, List<InstructorDto> instructorDtoList) {
        this.courseId = courseId;
        this.courseImage = courseImage;
        this.courseName = courseName;
        this.courseDuration = courseDuration;
        this.courseCost = courseCost;
        this.courseDescription = courseDescription;
        this.startDate = startDate;
        this.endDate = endDate;
        this.courseTypeName = courseTypeName;
        this.onlineCourseDto = onlineCourseDto;
        this.offlineCourseDto = offlineCourseDto;
        this.instructorDtoList = instructorDtoList;
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

    public OnlineCourseDto getOnlineCourseDto() {
        return onlineCourseDto;
    }

    public void setOnlineCourseDto(OnlineCourseDto onlineCourseDto) {
        this.onlineCourseDto = onlineCourseDto;
    }

    public OfflineCourseDto getOfflineCourseDto() {
        return offlineCourseDto;
    }

    public void setOfflineCourseDto(OfflineCourseDto offlineCourseDto) {
        this.offlineCourseDto = offlineCourseDto;
    }

    public List<InstructorDto> getInstructorDtoList() {
        return instructorDtoList;
    }

    public void setInstructorDtoList(List<InstructorDto> instructorDtoList) {
        this.instructorDtoList = instructorDtoList;
    }
}