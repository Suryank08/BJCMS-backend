package com.bjcms.entity.course;

import jakarta.persistence.*;

@Entity
@Table(name = "course_type")
public class CourseType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "course_type_id")
    private Integer courseTypeId;

    @Column(name = "course_type_name",nullable = false)
    private String courseTypeName;
//
//    @OneToMany(mappedBy = "courseType")
//    private List<Course> courseList;

    public CourseType(Integer courseTypeId, String courseTypeName) {
        this.courseTypeId = courseTypeId;
        this.courseTypeName = courseTypeName;
//        this.courseList = courseList;
    }

    public CourseType() {
    }

    public Integer getCourseTypeId() {
        return courseTypeId;
    }

    public void setCourseTypeId(Integer courseTypeId) {
        this.courseTypeId = courseTypeId;
    }

    public String getCourseTypeName() {
        return courseTypeName;
    }

    public void setCourseTypeName(String courseTypeName) {
        this.courseTypeName = courseTypeName;
    }

//    public List<Course> getCourseList() {
//        return courseList;
//    }
//
//    public void setCourseList(List<Course> courseList) {
//        this.courseList = courseList;
//    }
}
