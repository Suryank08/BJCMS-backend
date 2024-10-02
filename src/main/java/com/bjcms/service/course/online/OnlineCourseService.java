package com.bjcms.service.course.online;

import com.bjcms.entity.course.online.OnlineCourse;

import java.util.List;

public interface OnlineCourseService {
    List<OnlineCourse> addOnlineCourses(List<OnlineCourse> onlineCourseList);
    OnlineCourse updateOnlineCourse(OnlineCourse onlineCourse);
    void deleteOnlineCourse(int id);
    List<OnlineCourse> getAllOnlineCourses();
    OnlineCourse findOnlineCourse(int id);
}

