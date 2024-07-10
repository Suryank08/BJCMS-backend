package com.bjcms.service.course;

import java.util.List;

import com.bjcms.entity.course.CourseType;

public interface CourseTypeService {
    List<CourseType> addCourseType(List<CourseType> courseTypeList);
    CourseType updateCourseType(CourseType courseType);
    void deleteCourseType(int id);
    List<CourseType> getAllCourseTypes();
    CourseType findCourseType(int id);
}

