package com.bjcms.dto.instructor;

public class QualificationDto {
    private Integer qualificationId;
    private String qualificationName;

    public QualificationDto(Integer qualificationId, String qualificationName) {
        this.qualificationId = qualificationId;
        this.qualificationName = qualificationName;
    }

    public QualificationDto() {
    }

    public String getQualificationName() {
        return qualificationName;
    }

    public void setQualificationName(String qualificationName) {
        this.qualificationName = qualificationName;
    }

    public Integer getQualificationId() {
        return qualificationId;
    }

    public void setQualificationId(Integer qualificationId) {
        this.qualificationId = qualificationId;
    }
}
