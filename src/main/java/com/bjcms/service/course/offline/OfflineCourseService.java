package com.bjcms.service.course.offline;

import com.bjcms.entity.course.offline.OfflineCourse;

import java.util.List;

public interface OfflineCourseService {
    List<OfflineCourse> addOfflineCourses(List<OfflineCourse> offlineCourseList);
    OfflineCourse updateOfflineCourse(OfflineCourse offlineCourse);
    void deleteOfflineCourse(int id);
    List<OfflineCourse> getAllOfflineCourses();
    OfflineCourse findOfflineCourse(int id);
}

