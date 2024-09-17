package com.bjcms.service.course;



import java.util.List;
import java.util.Optional;

import com.bjcms.entity.course.Course;

public interface CourseService {
    public List<Course> addCourses(List<Course> courseList);
    public Course addCourse(Course course,String email);
    public void deleteCourse(int id);

    public Course updateCourse(Course course);

    public Course findCourse(int id);

    public List<Course> getAllCourse();
    public Optional<Course> findByCourseId(Integer id);
    public Course enrollStudentInCourse(Integer courseId, String email, Integer batchId);
    public List<Course>enrolledCourses(String userName);
    public List<Course>instructorCourses(String userName);
}