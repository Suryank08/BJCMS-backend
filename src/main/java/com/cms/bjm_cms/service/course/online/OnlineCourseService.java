package com.cms.bjm_cms.service.course.online;

import com.cms.bjm_cms.entity.course.online.OnlineCourse;

import java.util.List;

public interface OnlineCourseService {
    List<OnlineCourse> addOnlineCourses(List<OnlineCourse> onlineCourseList);
    OnlineCourse updateOnlineCourse(OnlineCourse onlineCourse);
    void deleteOnlineCourse(int id);
    List<OnlineCourse> getAllOnlineCourses();
    OnlineCourse findOnlineCourse(int id);
}

