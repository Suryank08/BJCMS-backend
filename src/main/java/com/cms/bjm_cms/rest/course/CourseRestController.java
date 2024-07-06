package com.cms.bjm_cms.rest.course;

import com.cms.bjm_cms.entity.course.Course;
import com.cms.bjm_cms.service.course.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
