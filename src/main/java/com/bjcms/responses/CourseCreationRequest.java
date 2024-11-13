package com.bjcms.responses;

import com.bjcms.entity.course.CourseType;
import com.bjcms.entity.course.offline.OfflineCourse;
import com.bjcms.entity.course.online.OnlineCourse;
import com.bjcms.entity.instructor.Instructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CourseCreationRequest {
    private String coachingId;
    private String courseImage;
    private String courseName;
    private String courseDuration;
    private String courseCost;
    private String courseDescription;
    private Date startDate;
    private Date endDate;
    private CourseType courseType;
    private OnlineCourse onlineCourse;
    private OfflineCourse offlineCourse;
    private List<Instructor> instructorList = new ArrayList<>();

    public CourseCreationRequest(String coachingId, String courseImage, String courseName, String courseDuration, String courseCost, String courseDescription, Date startDate, Date endDate, CourseType courseType, OnlineCourse onlineCourse, OfflineCourse offlineCourse, List<Instructor> instructorList) {
        this.coachingId = coachingId;
        this.courseImage = courseImage;
        this.courseName = courseName;
        this.courseDuration = courseDuration;
        this.courseCost = courseCost;
        this.courseDescription = courseDescription;
        this.startDate = startDate;
        this.endDate = endDate;
        this.courseType = courseType;
        this.onlineCourse = onlineCourse;
        this.offlineCourse = offlineCourse;
        this.instructorList = instructorList;
    }

    public String getCoachingId() {
        return coachingId;
    }

    public void setCoachingId(String coachingId) {
        this.coachingId = coachingId;
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

    public CourseType getCourseType() {
        return courseType;
    }

    public void setCourseType(CourseType courseType) {
        this.courseType = courseType;
    }

    public OnlineCourse getOnlineCourse() {
        return onlineCourse;
    }

    public void setOnlineCourse(OnlineCourse onlineCourse) {
        this.onlineCourse = onlineCourse;
    }

    public OfflineCourse getOfflineCourse() {
        return offlineCourse;
    }

    public void setOfflineCourse(OfflineCourse offlineCourse) {
        this.offlineCourse = offlineCourse;
    }

    public List<Instructor> getInstructorList() {
        return instructorList;
    }

    public void setInstructorList(List<Instructor> instructorList) {
        this.instructorList = instructorList;
    }
}
