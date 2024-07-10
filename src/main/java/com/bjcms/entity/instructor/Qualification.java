package com.bjcms.entity.instructor;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "qualification")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "qualificationId")
public class Qualification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "qualification_id")
    private Integer qualificationId;
    @Column(name = "qualification_name")
    private String qualificationName;
    @ManyToMany
    @JoinTable(name = "instructor_qualification",joinColumns = @JoinColumn(name = "qualification_id"),inverseJoinColumns = @JoinColumn(name = "instructor_id"))
    private List<Instructor> instructorList=new ArrayList<>();

    public Qualification() {
    }

    public Qualification(Integer qualificationId, String qualificationName, List<Instructor> instructorList) {
        this.qualificationId = qualificationId;
        this.qualificationName = qualificationName;
        this.instructorList = instructorList;
    }

    public Integer getQualificationId() {
        return qualificationId;
    }

    public void setQualificationId(Integer qualificationId) {
        this.qualificationId = qualificationId;
    }

    public String getQualificationName() {
        return qualificationName;
    }

    public void setQualificationName(String qualificationName) {
        this.qualificationName = qualificationName;
    }

    public List<Instructor> getInstructorList() {
        return instructorList;
    }

    public void setInstructorList(List<Instructor> instructorList) {
        this.instructorList = instructorList;
    }
}
