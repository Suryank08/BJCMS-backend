package com.bjcms.dto.course;
import java.util.ArrayList;
import java.util.List;

public class OnlineCourseDto {

    private Integer onlineCourseId;

    private String status = "upcoming";

    private List<SubjectDto> subjectList=new ArrayList<>();

    public OnlineCourseDto(Integer onlineCourseId, String status, List<SubjectDto> subjectList) {
        this.onlineCourseId = onlineCourseId;
        this.status = status;
        this.subjectList = subjectList;
    }

    public OnlineCourseDto() {
    }

    public Integer getOnlineCourseId() {
        return onlineCourseId;
    }

    public void setOnlineCourseId(Integer onlineCourseId) {
        this.onlineCourseId = onlineCourseId;
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
}
