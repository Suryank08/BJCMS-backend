package com.bjcms.rest.instructor;

import com.bjcms.entity.course.Subject;
import com.bjcms.entity.instructor.Qualification;
import com.bjcms.responses.SubjectQualificationFormResponse;
import com.bjcms.service.course.SubjectService;
import com.bjcms.service.instructor.QualificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/qualification")
public class QualificationRestController {
    private QualificationService qualificationService;
    private SubjectService subjectService;
    @Autowired
    public QualificationRestController(QualificationService qualificationService,SubjectService subjectService) {
        this.qualificationService = qualificationService;
       this.subjectService=subjectService;
    }

    @GetMapping("/get/subject-qualification")
    public SubjectQualificationFormResponse getAllQualification(){
        List<Qualification> qualificationList= qualificationService.getAllQualification();
       List<Subject> subjectList =subjectService.getAllSubject();
        SubjectQualificationFormResponse subjectQualificationFormResponse=new SubjectQualificationFormResponse(qualificationList,subjectList);
        return subjectQualificationFormResponse;
    }

}
