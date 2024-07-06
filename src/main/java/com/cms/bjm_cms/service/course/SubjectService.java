package com.cms.bjm_cms.service.course;

import com.cms.bjm_cms.entity.course.Subject;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface SubjectService {
    public List<Subject> addSubjects(List<Subject> subjectList);

    public Subject addSubject(Subject subject);

    public void deleteSubject(Integer id);

    public Subject updateSubject(Subject subject);

    public Subject findSubject(Integer id);

    public List<Subject> getAllSubject();

    public void deleteAllSubject();
    
    Optional<Subject> findBySubjectId(Integer id);
}

