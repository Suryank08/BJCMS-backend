package com.bjcms.rest.instructor;

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
import com.bjcms.entity.instructor.Instructor;
import com.bjcms.service.instructor.InstructorService;

@RestController
@RequestMapping("/api/instructors")
public class InstructorRestController {
    private final InstructorService instructorService;

    @Autowired
    public InstructorRestController(InstructorService instructorService) {
        this.instructorService = instructorService;
    }

    @PostMapping("/bulk-create")
    public List<Instructor> addInstructors(@RequestBody List<Instructor> instructorList) {
        System.out.println("Added Instructor");
        return instructorService.addInstructors(instructorList);
    }
    @PostMapping("/create")
    public Instructor addInstructor(@RequestBody Instructor instructor) {
        return instructorService.addInstructor(instructor);
    }
    @PostMapping("/update")
    public Instructor updateInstructor(@RequestBody Instructor instructor) {
        return instructorService.updateInstructor(instructor);
    }

    @GetMapping("/")
    public List<Instructor> getAllInstructor() {
        return instructorService.getAllInstructor();
    }

    @GetMapping("/{instructorId}")
    public Instructor getInstructor(@PathVariable int instructorId) {
        return instructorService.findInstructor(instructorId);
    }

    @GetMapping("/courses/{instructorId}")
    public List<Course> getAllInstructorCoursesById(@PathVariable int instructorId) {
        return instructorService.getAllInstructorCoursesById(instructorId);
    }


    @DeleteMapping("/delete/{instructorId}")
    public void deleteInstructor(@PathVariable int instructorId) {
        instructorService.deleteInstructor(instructorId);
        System.out.println("Instructor Deleted");
    }

    @DeleteMapping("/delete-all")
    public void deleteAllInstructor() {
        instructorService.deleteAllInstructor();
    }


}
