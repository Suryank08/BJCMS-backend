package com.cms.bjm_cms.rest.instructor;

import com.cms.bjm_cms.entity.course.Course;
import com.cms.bjm_cms.entity.instructor.Instructor;
import com.cms.bjm_cms.service.instructor.InstructorService;
import org.springframework.beans.factory.annotation.Autowired;
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
