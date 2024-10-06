package com.bjcms.responses;

import com.bjcms.dto.course.SubjectDto;
import com.bjcms.dto.instructor.QualificationDto;
import com.bjcms.entity.course.Subject;
import com.bjcms.entity.instructor.Qualification;

import java.util.List;

public class SubjectQualificationFormResponse {
   private List<QualificationDto> qualificationList;
   private List<SubjectDto> subjectList;

    public SubjectQualificationFormResponse(List<QualificationDto> qualificationList, List<SubjectDto> subjectList) {
        this.qualificationList = qualificationList;
        this.subjectList = subjectList;
    }

    public SubjectQualificationFormResponse() {
    }

    public List<QualificationDto> getQualificationList() {
        return qualificationList;
    }

    public void setQualificationList(List<QualificationDto> qualificationList) {
        this.qualificationList = qualificationList;
    }

    public List<SubjectDto> getSubjectList() {
        return subjectList;
    }

    public void setSubjectList(List<SubjectDto> subjectList) {
        this.subjectList = subjectList;
    }
}
