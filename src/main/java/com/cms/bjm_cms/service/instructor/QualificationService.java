package com.cms.bjm_cms.service.instructor;

import com.cms.bjm_cms.entity.instructor.Instructor;
import com.cms.bjm_cms.entity.instructor.Qualification;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface QualificationService {

    public Qualification findByQualificationName(String qualificationName);

    public List<Qualification> addInstructorQualification(List<Qualification> qualificationList);

    public Qualification addQualification(Qualification qualification);

    public List<Qualification> addQualifications(List<Qualification> qualificationList);

    public void deleteQualification(Integer id);

    public Qualification updateQualification(Qualification qualification);

    public Qualification findQualification(Integer id);
    
    public Optional<Qualification> findByQualificationId(Integer id);

    public List<Qualification> getAllQualification();
    
}
