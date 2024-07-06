package com.cms.bjm_cms.rest.course;

import com.cms.bjm_cms.entity.course.Subject;
import com.cms.bjm_cms.service.course.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public Subject addSubject(@RequestBody Subject subject) {
        return subjectService.addSubject(subject);
    }

    @PostMapping("/bulk-create")
    public List<Subject> addSubjectList(@RequestBody List<Subject> subjectList) {
        return subjectService.addSubjects(subjectList);
    }

    @PostMapping("/update")
    public Subject updateSubject(@RequestBody Subject subject) {
        return subjectService.updateSubject(subject);
    }

    @GetMapping("/")
    public List<Subject> getAllSubjects() {
        return subjectService.getAllSubject();
    }

    @GetMapping("/{subjectId}")
    public Subject getSubject(@PathVariable Integer subjectId) {
        return subjectService.findSubject(subjectId);
    }

    @DeleteMapping("/delete/{subjectId}")
    public void deleteSubject(@PathVariable Integer subjectId) {
        subjectService.deleteSubject(subjectId);
    }

    @DeleteMapping("/delete-all")
    public void deleteAllSubjects() {
        subjectService.deleteAllSubject();
    }
}
