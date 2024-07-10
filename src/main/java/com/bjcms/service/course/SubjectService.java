package com.bjcms.service.course;

import java.util.List;
import java.util.Optional;

import com.bjcms.entity.course.Subject;

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

