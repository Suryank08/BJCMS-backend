package com.bjcms.service.instructor;


import com.bjcms.dao.instructor.QualificationDao;
import com.bjcms.entity.instructor.Qualification;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class QualificationServiceImpl implements QualificationService {
    QualificationDao qualificationDao;

    @Autowired
    public QualificationServiceImpl(QualificationDao qualificationDao) {
        this.qualificationDao = qualificationDao;
    }

    public Qualification findByQualificationName(String qualificationName){
           return qualificationDao.findByQualificationName(qualificationName);

    }

    public List<Qualification> addInstructorQualification(List<Qualification> qualificationList){
        List<Qualification> tempQualificationList =new ArrayList<>();
        for(Qualification qualification:qualificationList){
          Qualification tempQualification=  findByQualificationName(qualification.getQualificationName());
          tempQualificationList.add(tempQualification);
        }
        return tempQualificationList;
    }

    @Transactional
    public Qualification addQualification(Qualification qualification){
        return qualificationDao.save(qualification);
    }

    @Transactional
    public List<Qualification> addQualifications(List<Qualification> qualifications) {
        for (Qualification qualification : qualifications) {
            qualificationDao.save(qualification);
        }
       return qualificationDao.findAll();
    }

    @Transactional
    public Qualification updateQualification(Qualification qualification) {
        return qualificationDao.save(qualification);
    }

    @Transactional
    public void deleteQualification(Integer id) {
        Optional<Qualification> optionalQualification = qualificationDao.findById(id);
        Qualification qualification = optionalQualification.orElse(new Qualification());
        qualificationDao.delete(qualification);
    }

    public List<Qualification> getAllQualification() {
        List<Qualification> qualificationList = qualificationDao.findAll();
        return qualificationList;
    }


    public Qualification findQualification(Integer id) {
        Optional<Qualification> optionalQualification = qualificationDao.findById(id);
        Qualification qualification = optionalQualification.orElse(new Qualification());
        return qualification;
    }
    public Optional<Qualification> findByQualificationId(Integer id){
        return qualificationDao.findById(id);
    }

    public List<Qualification> findAllQualificationBysIds(List<Integer> qualificationIdsList){
        return qualificationDao.findAllById(qualificationIdsList);
    }

}
