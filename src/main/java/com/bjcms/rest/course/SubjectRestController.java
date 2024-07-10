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

import com.bjcms.entity.course.Subject;
import com.bjcms.service.course.SubjectService;

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
