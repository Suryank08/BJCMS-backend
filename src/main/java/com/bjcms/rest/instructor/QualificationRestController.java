package com.bjcms.rest.instructor;

import com.bjcms.dto.course.SubjectDto;
import com.bjcms.dto.instructor.QualificationDto;
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
import java.util.stream.Collectors;

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
        List<QualificationDto> qualificationList= qualificationService.getAllQualification().stream().map(qualification -> new QualificationDto(qualification.getQualificationId(), qualification.getQualificationName())).collect(Collectors.toList());
       List<SubjectDto> subjectList =subjectService.getAllSubject().stream()
               .map(subject -> new SubjectDto(subject.getSubjectId(), subject.getSubjectName()))  // Map Subject to SubjectDto
               .collect(Collectors.toList());
        SubjectQualificationFormResponse subjectQualificationFormResponse=new SubjectQualificationFormResponse(qualificationList,subjectList);
        return subjectQualificationFormResponse;
    }

}
