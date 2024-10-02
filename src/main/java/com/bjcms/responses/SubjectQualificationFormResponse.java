package com.bjcms.responses;

import com.bjcms.entity.course.Subject;
import com.bjcms.entity.instructor.Qualification;

import java.util.List;

public class SubjectQualificationFormResponse {
   private List<Qualification> qualificationList;
   private List<Subject> subjectList;

    public SubjectQualificationFormResponse(List<Qualification> qualificationList, List<Subject> subjectList) {
        this.qualificationList = qualificationList;
        this.subjectList = subjectList;
    }

    public List<Qualification> getQualificationList() {
        return qualificationList;
    }

    public void setQualificationList(List<Qualification> qualificationList) {
        this.qualificationList = qualificationList;
    }

    public List<Subject> getSubjectList() {
        return subjectList;
    }

    public void setSubjectList(List<Subject> subjectList) {
        this.subjectList = subjectList;
    }
}
