package com.bjcms.service.course;


import com.bjcms.dto.course.CourseDto;
import com.bjcms.entity.course.Course;
import com.bjcms.responses.CourseCreationRequest;

import java.util.List;
import java.util.Optional;

public interface CourseService {
    public List<Course> addCourses(List<Course> courseList);
    public Course addCourse(CourseCreationRequest courseCreationRequest, String email);
    public void deleteCourse(int id);

    public Course updateCourse(Course course);

    public Course findCourse(int id);

    public List<Course> getAllCourseByCoachingId( Integer coachingId);
    public Optional<Course> findByCourseId(Integer id);
    public Course enrollStudentInCourse(Integer courseId, String email, Integer batchId);
    public List<Course>enrolledCourses(String userName);
    public List<CourseDto>instructorCourses(String userName);
}