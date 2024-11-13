package com.bjcms.responses;

import java.util.ArrayList;
import java.util.List;

public class AttendanceRequest {
    private Integer courseId;
    private Integer subjectId;
    private Integer batchId;
    private List<Integer> presentIds =new ArrayList<>();
    private List<Integer> absentIds=new ArrayList<>();

    public AttendanceRequest(Integer courseId, Integer subjectId, Integer batchId, List<Integer> presentIds, List<Integer> absentIds) {
        this.courseId = courseId;
        this.subjectId = subjectId;
        this.batchId = batchId;
        this.presentIds = presentIds;
        this.absentIds = absentIds;
    }

    public AttendanceRequest() {
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public Integer getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Integer subjectId) {
        this.subjectId = subjectId;
    }

    public Integer getBatchId() {
        return batchId;
    }

    public void setBatchId(Integer batchId) {
        this.batchId = batchId;
    }

    public List<Integer> getPresentIds() {
        return presentIds;
    }

    public void setPresentIds(List<Integer> presentIds) {
        this.presentIds = presentIds;
    }

    public List<Integer> getAbsentIds() {
        return absentIds;
    }

    public void setAbsentIds(List<Integer> absentIds) {
        this.absentIds = absentIds;
    }

    @Override
    public String toString() {
        return "AttendanceRequest{" +
                "courseId=" + courseId +
                ", subjectId=" + subjectId +
                ", batchId=" + batchId +
                ", presentIds=" + presentIds +
                ", absentIds=" + absentIds +
                '}';
    }
}
