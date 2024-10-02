package com.bjcms.rest.instructor;

import com.bjcms.entity.course.Course;
import com.bjcms.entity.instructor.Instructor;
import com.bjcms.responses.InstructorCreateRequest;
import com.bjcms.service.instructor.InstructorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/instructors")
public class InstructorRestController {
    private final InstructorService instructorService;

    @Autowired
    public InstructorRestController(InstructorService instructorService) {
        this.instructorService = instructorService;
    }


    @PostMapping("/create")
    @PreAuthorize("hasAnyAuthority('ADMIN','CO-ADMIN')")
    public void addInstructor(@RequestBody InstructorCreateRequest instructorRequest ) {
     instructorService.addInstructor(instructorRequest);
    }
    @PostMapping("/update")
    @PreAuthorize("hasAnyAuthority('ADMIN','INSTRUCTOR')")
    public Instructor updateInstructor(@RequestBody Instructor instructor) {
        return instructorService.updateInstructor(instructor);
    }

    @GetMapping("/")
    @PreAuthorize("hasAnyAuthority('ADMIN','INSTRUCTOR','STUDENT','USER')")
    public List<Instructor> getAllInstructor() {
        return instructorService.getAllInstructor();
    }

    @GetMapping("/{instructorId}")
    @PreAuthorize("hasAnyAuthority('ADMIN','INSTRUCTOR','STUDENT','USER')")
    public Instructor getInstructor(@PathVariable int instructorId) {
        return instructorService.findInstructor(instructorId);
    }

    @GetMapping("/courses/{instructorId}")
    @PreAuthorize("hasAnyAuthority('ADMIN','INSTRUCTOR','STUDENT','USER')")
    public List<Course> getAllInstructorCoursesById(@PathVariable int instructorId) {
        return instructorService.getAllInstructorCoursesById(instructorId);
    }


    @DeleteMapping("/delete/{instructorId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public void deleteInstructor(@PathVariable int instructorId) {
        instructorService.deleteInstructor(instructorId);
        System.out.println("Instructor Deleted");
    }
}
