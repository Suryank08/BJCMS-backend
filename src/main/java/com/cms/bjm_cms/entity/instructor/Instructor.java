package com.cms.bjm_cms.entity.instructor;

import com.cms.bjm_cms.entity.course.Course;
import com.cms.bjm_cms.entity.course.Subject;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="instructor")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "instructorId")
public class Instructor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="instructor_id")
    private Integer instructorId;
    @Column(name = "instructor_name")
    private String instructorName;

    @OneToOne(mappedBy = "instructor",cascade = CascadeType.ALL,orphanRemoval = true)
    @JsonManagedReference
    private InstructorInfo instructorInfo;

    @ManyToMany(cascade ={CascadeType.PERSIST,CascadeType.MERGE},fetch = FetchType.LAZY)
    @JoinTable(name = "instructor_qualification",joinColumns = @JoinColumn(name = "instructor_id"),inverseJoinColumns = @JoinColumn(name = "qualification_id"))
   private List<Qualification> qualificationList =new ArrayList<>();

   @ManyToMany(cascade ={CascadeType.PERSIST,CascadeType.MERGE},fetch = FetchType.LAZY)
    @JoinTable(name = "instructor_subject",joinColumns = @JoinColumn(name = "instructor_id"),inverseJoinColumns = @JoinColumn(name = "subject_id"))
   private List<Subject> subjectList=new ArrayList<>();

    @ManyToMany(cascade ={CascadeType.PERSIST,CascadeType.MERGE},fetch = FetchType.LAZY)
    @JoinTable(name = "instructor_course",joinColumns = @JoinColumn(name = "instructor_id"),inverseJoinColumns = @JoinColumn(name = "course_id"))
    private List<Course> courseList=new ArrayList<>();

    public Instructor(Integer instructorId, String instructorName, InstructorInfo instructorInfo, List<Qualification> qualificationList, List<Subject> subjectList, List<Course> courseList) {
        this.instructorId = instructorId;
        this.instructorName = instructorName;
        this.instructorInfo = instructorInfo;
        this.qualificationList = qualificationList;
        this.subjectList = subjectList;
        this.courseList = courseList;
    }

    public Instructor() {
    }

    public Integer getInstructorId() {
        return instructorId;
    }

    public void setInstructorId(Integer instructorId) {
        this.instructorId = instructorId;
    }

    public String getInstructorName() {
        return instructorName;
    }

    public void setInstructorName(String instructorName) {
        this.instructorName = instructorName;
    }

    public InstructorInfo getInstructorInfo() {
        return instructorInfo;
    }

    public void setInstructorInfo(InstructorInfo instructorInfo) {
        this.instructorInfo = instructorInfo;
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

    public List<Course> getCourseList() {
        return courseList;
    }

    public void setCourseList(List<Course> courseList) {
        this.courseList = courseList;
    }

}