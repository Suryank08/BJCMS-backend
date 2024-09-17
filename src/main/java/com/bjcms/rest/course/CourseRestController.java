package com.bjcms.rest.course;

import java.security.Principal;
import java.util.List;

import com.bjcms.responses.EnrollmentRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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
@RequestMapping("/api/courses")
public class CourseRestController {
    private CourseService courseService;

    @Autowired
    public CourseRestController(CourseService courseService) {
        this.courseService = courseService;
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','INSTRUCTOR','STUDENT','USER')")
    @GetMapping("/")
    public List<Course> getAllCourse(){
        return courseService.getAllCourse();
    }


    @GetMapping("/{courseId}")
    @PreAuthorize("hasAnyAuthority('ADMIN','INSTRUCTOR','STUDENT','USER')")
    public Course getCourse(@PathVariable int courseId){
        return courseService.findCourse(courseId);
    }

    @GetMapping("/enrolledCourses")
    @PreAuthorize("hasAnyAuthority('ADMIN','INSTRUCTOR','STUDENT')")
    public  List<Course> enrolledCourse(Principal principal){
        System.out.println("conntroller enrolled Courses");
        String userName=principal.getName();
        return courseService.enrolledCourses(userName);
    }

    @GetMapping("/instructorCourses")
    @PreAuthorize("hasAnyAuthority('ADMIN','INSTRUCTOR')")
    public  List<Course> instructorCourse(Principal principal){
        System.out.println("conntroller get Instructor Courses");
        String userName=principal.getName();
        return courseService.instructorCourses(userName);
    }

    @PostMapping("/create")
   @PreAuthorize("hasAnyAuthority('ADMIN','INSTRUCTOR')")
    public Course addCourse(@RequestBody Course course,Principal principal){
        String email =principal.getName();
        return courseService.addCourse(course,email);
    }

    //TODO Protect this method
    @PostMapping("/enrollStudent")
    public Course enrollStudentInCourse(@RequestBody EnrollmentRequest enrollmentData ){
        System.out.println("conntroller metholdsdjcbj");
        Integer courseId = enrollmentData.getCourseId();
        String email= enrollmentData.getEmail();
        Integer batchId=enrollmentData.getBatchId();
        return courseService.enrollStudentInCourse(courseId,email,batchId);
    }


    @PostMapping("/update")
    @PreAuthorize("hasAnyAuthority('ADMIN','INSTRUCTOR')")
    public Course updateCourse(@RequestBody Course course){
        return courseService.updateCourse(course);
    }

    @DeleteMapping("/delete/{courseId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public void deleteCourse(@PathVariable int courseId){
        courseService.deleteCourse(courseId);
        System.out.println("Course Deleted");
    }
   }
