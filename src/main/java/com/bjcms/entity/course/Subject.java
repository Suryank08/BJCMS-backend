package com.bjcms.entity.course;

import java.util.ArrayList;
import java.util.List;

import com.bjcms.entity.course.offline.OfflineCourse;
import com.bjcms.entity.course.online.OnlineCourse;
import com.bjcms.entity.course.online.Video;
import com.bjcms.entity.instructor.Instructor;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "subject")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "subjectId")
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "subject_id")
    private Integer subjectId;
    @Column(name = "subject_name")
    private String subjectName;

    @ManyToMany(cascade ={CascadeType.PERSIST,CascadeType.MERGE},fetch = FetchType.LAZY)
    @JoinTable(name="instructor_subject",joinColumns = @JoinColumn(name = "subject_id"),inverseJoinColumns = @JoinColumn(name = "instructor_id"))
    private List<Instructor>instructorList=new ArrayList<>();

    @ManyToMany(cascade ={CascadeType.PERSIST,CascadeType.MERGE},fetch = FetchType.LAZY)
    @JoinTable(name="online_course_subject",joinColumns = @JoinColumn(name = "subject_id"),inverseJoinColumns = @JoinColumn(name = "online_course_id"))
    private List<OnlineCourse> onlineCourseList=new ArrayList<>();

    @ManyToMany(cascade ={CascadeType.PERSIST,CascadeType.MERGE},fetch = FetchType.LAZY)
    @JoinTable(name="offline_course_subject",joinColumns = @JoinColumn(name = "subject_id"),inverseJoinColumns = @JoinColumn(name = "course_id"))
    private List<OfflineCourse> offlineCourseList =new ArrayList<>();

    @OneToMany(mappedBy = "subject",cascade ={CascadeType.PERSIST,CascadeType.MERGE},fetch = FetchType.LAZY)
    private List<Video> videoList=new ArrayList<>();


    public Subject(Integer subjectId, String subjectName, List<Instructor> instructorList, List<OnlineCourse> onlineCourseList, List<OfflineCourse> offlineCourseList, List<Video> videoList) {
        this.subjectId = subjectId;
        this.subjectName = subjectName;
        this.instructorList = instructorList;
        this.onlineCourseList = onlineCourseList;
        this.offlineCourseList = offlineCourseList;
        this.videoList = videoList;
    }

    public Subject() {
    }

    public Integer getSubjectId() {
               return subjectId;
    }

    public void setSubjectId(Integer subjectId) {
        this.subjectId = subjectId;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public List<Instructor> getInstructorList() {
        return instructorList;
    }

    public void setInstructorList(List<Instructor> instructorList) {
        this.instructorList = instructorList;
    }

    public List<OnlineCourse> getOnlineCourseList() {
        return onlineCourseList;
    }

    public void setOnlineCourseList(List<OnlineCourse> onlineCourseList) {
        this.onlineCourseList = onlineCourseList;
    }

    public List<OfflineCourse> getOfflineCourseList() {
        return offlineCourseList;
    }

    public void setOfflineCourseList(List<OfflineCourse> offlineCourseList) {
        this.offlineCourseList = offlineCourseList;
    }

    public List<Video> getVideoList() {
        return videoList;
    }

    public void setVideoList(List<Video> videoList) {
        this.videoList = videoList;
    }
}
