package com.bjcms.service.course;

import com.bjcms.entity.course.CourseType;

import java.util.List;

public interface CourseTypeService {
    List<CourseType> addCourseType(List<CourseType> courseTypeList);
    CourseType updateCourseType(CourseType courseType);
    void deleteCourseType(int id);
    List<CourseType> getAllCourseTypes();
    CourseType findCourseType(int id);
}

