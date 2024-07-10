package com.bjcms.rest.course;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bjcms.entity.course.Course;
import com.bjcms.service.course.CourseService;

@RestController
@RequestMapping("/")
public class CourseRestController {
    private CourseService courseService;

    @Autowired
    public CourseRestController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping("/courses")
    public List<Course> getAllCourse(){
        return courseService.getAllCourse();
    }
    @GetMapping("/courses/{courseId}")
    public Course getCourse(@PathVariable int courseId){
        return courseService.findCourse(courseId);
    }
    @PostMapping("/courses")
    public List<Course> addCourse(@RequestBody List<Course> courseList){
        System.out.println("Added Course");
        return courseService.addCourses(courseList);

    }
    @DeleteMapping("/courses/{courseId}")
    public void deleteCourse(@PathVariable int courseId){
        courseService.deleteCourse(courseId);
        System.out.println("Course Deleted");
    }
    @PostMapping("/courses/update")
    public Course updateCourse(@RequestBody Course course){
        return courseService.updateCourse(course);
    }
}
