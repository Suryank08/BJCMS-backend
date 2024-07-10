package com.bjcms.service.instructor;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bjcms.dao.instructor.InstructorInfoDao;
import com.bjcms.entity.instructor.InstructorInfo;

import jakarta.transaction.Transactional;
@Service
public class InstructorInfoServiceImpl implements InstructorInfoService{
    InstructorInfoDao instructorInfoDao;

    @Autowired
    public InstructorInfoServiceImpl(InstructorInfoDao instructorInfoDao) {
        this.instructorInfoDao = instructorInfoDao;
    }

    @Transactional
    public InstructorInfo addInstructorInfo(InstructorInfo instructorInfo) {

        return instructorInfoDao.save(instructorInfo);
    }

    @Transactional
    public InstructorInfo updateInstructorInfo(InstructorInfo instructorInfo) {
        return instructorInfoDao.save(instructorInfo);
    }

    @Transactional
    public void deleteInstructorInfo(Integer id) {
        Optional<InstructorInfo> optionalInstructorInfo = instructorInfoDao.findById(id);
        InstructorInfo instructorInfo = optionalInstructorInfo.orElse(new InstructorInfo());
        instructorInfoDao.delete(instructorInfo);
    }

    public List<InstructorInfo> getAllInstructorInfo() {
        List<InstructorInfo> instructorInfoList = instructorInfoDao.findAll();
        return instructorInfoList;
    }


    public InstructorInfo findInstructorInfo(Integer id) {
        Optional<InstructorInfo> optionalInstructorInfo = instructorInfoDao.findById(id);
        InstructorInfo instructorInfo = optionalInstructorInfo.orElse(new InstructorInfo());
        return instructorInfo;
    }
    public Optional<InstructorInfo> findById(Integer instructorId){
        return instructorInfoDao.findById(instructorId);
    }
}
