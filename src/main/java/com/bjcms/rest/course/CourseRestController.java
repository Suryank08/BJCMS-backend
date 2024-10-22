package com.bjcms.rest.course;

import com.bjcms.dto.course.*;
import com.bjcms.dto.instructor.InstructorDto;
import com.bjcms.entity.course.Course;
import com.bjcms.responses.CourseCreationRequest;
import com.bjcms.responses.EnrollmentRequest;
import com.bjcms.service.course.CourseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.*;

@RestController
@RequestMapping("/api/courses")
public class CourseRestController {
    private static final Logger log = LoggerFactory.getLogger(CourseRestController.class);
    private CourseService courseService;

    @Autowired
    public CourseRestController(CourseService courseService) {
        this.courseService = courseService;
    }

   @PreAuthorize("hasAnyAuthority('ADMIN','INSTRUCTOR','STUDENT','USER','CO-ADMIN')")
    @GetMapping("/getByCoachingId")
    public ResponseEntity<List<CourseSummaryDto>> getAllCourseByCoachingId(@RequestParam("coachingId") Integer coachingId){
        List<CourseSummaryDto> courseSummaryDtoList = courseService.getAllCoursesSummaryByCoachingId(coachingId);
   return ResponseEntity.ok(courseSummaryDtoList);

    }


    @GetMapping("/getCourseById/{courseId}")
    @PreAuthorize("hasAnyAuthority('ADMIN','INSTRUCTOR','STUDENT','USER')")
    public CourseDetailedDto getCourse(@PathVariable int courseId){
       Course course= courseService.findCourse(courseId);
        return CourseUtil.parseCourseData(course);}

    @GetMapping("/enrolledCourses")
    @PreAuthorize("hasAnyAuthority('ADMIN','INSTRUCTOR','STUDENT')")
    public  ResponseEntity<List<CourseSummaryDto>> enrolledCourse(Principal principal){
        System.out.println("conntroller enrolled Courses");
        String userName=principal.getName();
        List<Course> coureList=courseService.enrolledCourses(userName);
        List<CourseSummaryDto> courseSummaryDtoList= CourseUtil.parseCourseSummaryDto(coureList);
        return ResponseEntity.ok(courseSummaryDtoList);
    }

    @GetMapping("/instructorCourses")
    @PreAuthorize("hasAnyAuthority('ADMIN','CO-ADMIN','INSTRUCTOR')")
    public  ResponseEntity<List<CourseDto>> instructorCourse(Principal principal){
        System.out.println("controller get Instructor Courses");
        if (principal == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        String userName = principal.getName();
        return ResponseEntity.ok(courseService.instructorCourses(userName));
    }

    @PostMapping("/create")
   @PreAuthorize("hasAnyAuthority('ADMIN','INSTRUCTOR')")
    public Course addCourse(@RequestBody CourseCreationRequest courseCreationRequest, Principal principal){
        String email =principal.getName();
        return courseService.addCourse(courseCreationRequest,email);
    }

    //TODO Protect this method
    @PostMapping("/enrollStudent")
    public ResponseEntity<Map<String, String>> enrollStudentInCourse(@RequestBody EnrollmentRequest enrollmentData) {
        System.out.println("Controller method triggered");
        Integer courseId = enrollmentData.getCourseId();
        String email = enrollmentData.getEmail();
        Integer batchId = enrollmentData.getBatchId();

        Course course = courseService.enrollStudentInCourse(courseId, email, batchId);

        // Create a JSON response as a map
        Map<String, String> response = new HashMap<>();
        response.put("message", "Student Enrolled in Course Successfully");

        return ResponseEntity.ok(response); // Return JSON response
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
