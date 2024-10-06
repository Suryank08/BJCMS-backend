package com.bjcms.dto.coaching;

public class CoachingDto {
    private Integer coachingId;
    private String coachingName;
    private String coachingVision;
    private String coachingAddress;
    private Integer totalCourses;
    private Integer totalStudents;
    private Integer totalInstructor;

    public CoachingDto(Integer coachingId, String coachingName, String coachingVision, String coachingAddress, Integer totalCourses, Integer totalStudents, Integer totalInstructor) {
        this.coachingId = coachingId;
        this.coachingName = coachingName;
        this.coachingVision = coachingVision;
        this.coachingAddress = coachingAddress;
        this.totalCourses = totalCourses;
        this.totalStudents = totalStudents;
        this.totalInstructor = totalInstructor;
    }

    public Integer getCoachingId() {
        return coachingId;
    }

    public void setCoachingId(Integer coachingId) {
        this.coachingId = coachingId;
    }

    public String getCoachingName() {
        return coachingName;
    }

    public void setCoachingName(String coachingName) {
        this.coachingName = coachingName;
    }

    public String getCoachingVision() {
        return coachingVision;
    }

    public void setCoachingVision(String coachingVision) {
        this.coachingVision = coachingVision;
    }

    public String getCoachingAddress() {
        return coachingAddress;
    }

    public void setCoachingAddress(String coachingAddress) {
        this.coachingAddress = coachingAddress;
    }

    public Integer getTotalCourses() {
        return totalCourses;
    }

    public void setTotalCourses(Integer totalCourses) {
        this.totalCourses = totalCourses;
    }

    public Integer getTotalStudents() {
        return totalStudents;
    }

    public void setTotalStudents(Integer totalStudents) {
        this.totalStudents = totalStudents;
    }

    public Integer getTotalInstructor() {
        return totalInstructor;
    }

    public void setTotalInstructor(Integer totalInstructor) {
        this.totalInstructor = totalInstructor;
    }
}
