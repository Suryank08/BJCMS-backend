package com.bjcms.entity.course;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.bjcms.entity.course.offline.OfflineCourse;
import com.bjcms.entity.course.online.OnlineCourse;
import com.bjcms.entity.instructor.Instructor;
import com.fasterxml.jackson.annotation.*;

import jakarta.persistence.*;

//@Data
//@Builder
//@AllArgsConstructor
//@NoArgsConstructor
@Entity
@Table(name = "course")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "courseId")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "course_id")
    private Integer courseId;
    @Column(name = "course_image")
    private String courseImage;
    @Column(name = "course_name",nullable = false)
    private String courseName;
    @Column(name = "course_duration")
    private String courseDuration;
    @Column(name = "course_cost")
    private String courseCost;
    @Column(name = "course_description")
    private String courseDescription;
    //TODO make it date from localDate class using value of method and convert string from json to localDate using parse method of local DAte Class in Service Pacakage or Rest Controller
    @Column(name = "start_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date startDate;
    @Column(name = "end_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date endDate;

    @ManyToOne(cascade ={CascadeType.MERGE,CascadeType.PERSIST})
    @JoinColumn(name = "course_type_id")
    private CourseType courseType;

    @OneToOne(mappedBy = "course",fetch = FetchType.EAGER)
    private OnlineCourse onlineCourse;

    @OneToOne(mappedBy = "course",fetch = FetchType.EAGER)
    private OfflineCourse offlineCourse;

    @ManyToMany(fetch = FetchType.LAZY)
    @JsonManagedReference
    @JoinTable(name = "instructor_course", joinColumns = @JoinColumn(name = "course_id"),inverseJoinColumns = @JoinColumn(name = "instructor_id"))
    List<Instructor> instructorList =new ArrayList<>();

    public Course(Integer courseId, String courseImage,String courseName, String courseDuration, String courseCost, String courseDescription, Date startDate, Date endDate, CourseType courseType, OnlineCourse onlineCourse, OfflineCourse offlineCourse, List<Instructor> instructorList) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.courseDuration = courseDuration;
        this.courseCost = courseCost;
        this.courseDescription = courseDescription;
        this.startDate = startDate;
        this.endDate = endDate;
        this.courseType = courseType;
        this.onlineCourse = onlineCourse;
        this.offlineCourse = offlineCourse;
        this.instructorList = instructorList;
        this.courseImage=courseImage;
    }

    public Course() {
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public String getCourseImage() {
        return courseImage;
    }

    public void setCourseImage(String courseImage) {
        this.courseImage = courseImage;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseDuration() {
        return courseDuration;
    }

    public void setCourseDuration(String courseDuration) {
        this.courseDuration = courseDuration;
    }

    public String getCourseCost() {
        return courseCost;
    }

    public void setCourseCost(String courseCost) {
        this.courseCost = courseCost;
    }

    public String getCourseDescription() {
        return courseDescription;
    }

    public void setCourseDescription(String courseDescription) {
        this.courseDescription = courseDescription;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public CourseType getCourseType() {
        return courseType;
    }

    public void setCourseType(CourseType courseType) {
        this.courseType = courseType;
    }

    public OnlineCourse getOnlineCourse() {
        return onlineCourse;
    }

    public void setOnlineCourse(OnlineCourse onlineCourse) {
        this.onlineCourse = onlineCourse;
    }

    public OfflineCourse getOfflineCourse() {
        return offlineCourse;
    }

    public void setOfflineCourse(OfflineCourse offlineCourse) {
        this.offlineCourse = offlineCourse;
    }

    public List<Instructor> getInstructorList() {
        return instructorList;
    }

    public void setInstructorList(List<Instructor> instructorList) {
        this.instructorList = instructorList;
    }

    @Override
    public String toString() {
        return "Course{" +
                "courseId=" + courseId +
                ", courseImage='" + courseImage + '\'' +
                ", courseName='" + courseName + '\'' +
                ", courseDuration='" + courseDuration + '\'' +
                ", courseCost='" + courseCost + '\'' +
                ", courseDescription='" + courseDescription + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", courseType=" + courseType +
                ", onlineCourse=" + onlineCourse +
                ", offlineCourse=" + offlineCourse +
                ", instructorList=" + instructorList +
                '}';
    }
}
