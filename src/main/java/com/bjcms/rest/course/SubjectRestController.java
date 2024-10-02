package com.bjcms.rest.course;

import com.bjcms.entity.course.Subject;
import com.bjcms.service.course.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/subjects")
public class SubjectRestController {
    private SubjectService subjectService;

    @Autowired
    public SubjectRestController(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @PostMapping("/create")
    @PreAuthorize("hasAnyAuthority('ADMIN','INSTRUCTOR')")
    public Subject addSubject(@RequestBody Subject subject) {
        return subjectService.addSubject(subject);
    }

    @PostMapping("/bulk-create")
    @PreAuthorize("hasAnyAuthority('ADMIN','INSTRUCTOR')")
    public List<Subject> addSubjectList(@RequestBody List<Subject> subjectList) {
        return subjectService.addSubjects(subjectList);
    }

    @PostMapping("/update")
    @PreAuthorize("hasAnyAuthority('ADMIN','INSTRUCTOR')")
    public Subject updateSubject(@RequestBody Subject subject) {
        return subjectService.updateSubject(subject);
    }

    @GetMapping("/")
    @PreAuthorize("hasAnyAuthority('ADMIN','INSTRUCTOR','STUDENT','USER')")
    public List<Subject> getAllSubjects() {
        return subjectService.getAllSubject();
    }

    @GetMapping("/{subjectId}")
    @PreAuthorize("hasAnyAuthority('ADMIN','INSTRUCTOR','STUDENT','USER')")
    public Subject getSubject(@PathVariable Integer subjectId) {
        return subjectService.findSubject(subjectId);
    }

    @DeleteMapping("/delete/{subjectId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public void deleteSubject(@PathVariable Integer subjectId) {
        subjectService.deleteSubject(subjectId);
    }

}
