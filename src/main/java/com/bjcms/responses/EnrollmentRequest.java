package com.bjcms.responses;

public class EnrollmentRequest {
   private Integer courseId;
    private String email;
  private  Integer batchId;

    public EnrollmentRequest(Integer courseId, String email, Integer batchId) {
        this.courseId = courseId;
        this.email = email;
        this.batchId = batchId;
    }

    public EnrollmentRequest() {
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getBatchId() {
        return batchId;
    }

    public void setBatchId(Integer batchId) {
        this.batchId = batchId;
    }
}
