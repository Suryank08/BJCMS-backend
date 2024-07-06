package com.cms.bjm_cms.service.course;



import com.cms.bjm_cms.entity.course.Course;

import java.util.List;
import java.util.Optional;

public interface CourseService {
    public List<Course> addCourses(List<Course> courseList);
    public Course addCourse(Course course);
    public void deleteCourse(int id);

    public Course updateCourse(Course course);

    public Course findCourse(int id);

    public List<Course> getAllCourse();
    public Optional<Course> findByCourseId(Integer id);
}