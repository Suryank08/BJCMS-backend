package com.bjcms.dto.course;

import java.util.ArrayList;
import java.util.List;

public class OfflineCourseDto {

    private Integer courseId;

    private String status = null;

    private List<SubjectDto> subjectList=new ArrayList<>();

     private List<BatchDto>batchList=new ArrayList<>();

    public OfflineCourseDto(Integer courseId, String status, List<SubjectDto> subjectList, List<BatchDto> batchList) {
        this.courseId = courseId;
        this.status = status;
        this.subjectList = subjectList;
        this.batchList = batchList;
    }

    public OfflineCourseDto() {
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<SubjectDto> getSubjectList() {
        return subjectList;
    }

    public void setSubjectList(List<SubjectDto> subjectList) {
        this.subjectList = subjectList;
    }

    public List<BatchDto> getBatchList() {
        return batchList;
    }

    public void setBatchList(List<BatchDto> batchList) {
        this.batchList = batchList;
    }
}
