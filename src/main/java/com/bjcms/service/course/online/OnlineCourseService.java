package com.bjcms.service.course.online;

import java.util.List;

import com.bjcms.entity.course.online.OnlineCourse;

public interface OnlineCourseService {
    List<OnlineCourse> addOnlineCourses(List<OnlineCourse> onlineCourseList);
    OnlineCourse updateOnlineCourse(OnlineCourse onlineCourse);
    void deleteOnlineCourse(int id);
    List<OnlineCourse> getAllOnlineCourses();
    OnlineCourse findOnlineCourse(int id);
}

