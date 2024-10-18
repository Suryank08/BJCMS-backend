package com.bjcms.rest.instructor;

import com.bjcms.dto.coaching.CoachingDto;
import com.bjcms.dto.instructor.InstructorDetailedDto;
import com.bjcms.dto.instructor.InstructorDto;
import com.bjcms.entity.course.Course;
import com.bjcms.entity.instructor.Instructor;
import com.bjcms.responses.InstructorCreateRequest;
import com.bjcms.service.instructor.InstructorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/instructors")
public class InstructorRestController {
    private final InstructorService instructorService;
    private static final Logger logger = LoggerFactory.getLogger(InstructorRestController.class);
    @Autowired
    public InstructorRestController(InstructorService instructorService) {
        this.instructorService = instructorService;
    }

//TODO LAter replace try and catch blocks with golbal exception handeler
    @PostMapping("/create")
    @PreAuthorize("hasAnyAuthority('ADMIN','CO-ADMIN')")
    public ResponseEntity<String> addInstructor(@RequestBody InstructorCreateRequest instructorRequest ) {
    try{
        instructorService.addInstructor(instructorRequest);
        return ResponseEntity.ok("Instructor saved successfully");
    } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body( e.getMessage());
    }
    }

    @GetMapping("/coachingInstructors")
    @PreAuthorize("hasAnyAuthority('ADMIN','CO-ADMIN')")
    public ResponseEntity<List<InstructorDto>> getInstructorsByCoachingId(@RequestParam Integer coachingId) {
        // Log coachingId for debugging
        System.out.println("coachingId: " + coachingId);

        List<InstructorDto> instructorDtoList = instructorService.getInstructorsByCoachingId(coachingId);

        if (instructorDtoList != null && !instructorDtoList.isEmpty()) {
            return ResponseEntity.ok(instructorDtoList);
        } else {
            return ResponseEntity.noContent().build();  // Return 204 if no instructors found
        }
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
    public InstructorDetailedDto getInstructor(@PathVariable int instructorId) {
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

    @GetMapping("/get/coachingName")
    @PreAuthorize("hasAuthority('INSTRUCTOR')")
    public ResponseEntity<List<CoachingDto>> getCoachingOfInstructor(Principal principal){
        String email=principal.getName();
        List<CoachingDto> coachingDto=instructorService.getCoachingOfInstructor(email);
        return ResponseEntity.ok(coachingDto);
    }
}
