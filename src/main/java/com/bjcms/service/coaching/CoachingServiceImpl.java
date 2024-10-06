package com.bjcms.service.coaching;

import com.bjcms.dao.coaching.CoachingAdminDao;
import com.bjcms.dao.coaching.CoachingDao;
import com.bjcms.dto.coaching.CoachingDto;
import com.bjcms.entity.coaching.Coaching;
import com.bjcms.entity.coaching.CoachingAdmin;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CoachingServiceImpl implements CoachingService {
    private CoachingDao coachingDao;
    private CoachingAdminDao coachingAdminDao;

    @Autowired
    public CoachingServiceImpl(CoachingDao coachingDao, CoachingAdminDao coachingAdminDao) {
        this.coachingDao = coachingDao;
        this.coachingAdminDao = coachingAdminDao;
    }


    public Coaching findCoachingByCoachingName(String coachingName) {
        return coachingDao.findByCoachingName(coachingName);
    }

    @Transactional
    public Coaching addCoaching(String email, Coaching coaching) {
        try {
            CoachingAdmin coachingAdmin = coachingAdminDao.findByCoachingAdminEmail(email).orElseThrow(() -> new IllegalArgumentException("CO-ADMIN not found"));
            Coaching savedCoaching;
            if (coachingAdmin != null) {
                coaching.setCoachingAdmin(coachingAdmin);
                savedCoaching = coachingDao.save(coaching);
                coachingAdmin.setCoaching(savedCoaching);
                coachingAdminDao.save(coachingAdmin);
                return savedCoaching;
            } else {
                throw new RuntimeException("Not Having Authority");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public Coaching findCoachingByCoachingId(Integer coachingId){
    return coachingDao.findById(coachingId).orElseThrow(()->new IllegalArgumentException("Coaching Id Not Found"));
    }

    public CoachingDto getCoachingOfCoachingAdmin(String email) {
            CoachingAdmin coachingAdmin = coachingAdminDao.findByCoachingAdminEmail(email).orElseThrow(() -> new IllegalArgumentException("Coaching Admin Do not Exist"));
       Coaching coaching=coachingAdmin.getCoaching();
        Integer totalInstructor=coaching.getInstructorList().size();
        Integer totalStudent=coaching.getStudentList().size();
        Integer totalCourse=coaching.getCoursesList().size();
        CoachingDto coachingDto=new CoachingDto(coaching.getCoachingId(),coaching.getCoachingName(), coaching.getCoachingVision(), coaching.getCoachingAddress(),totalCourse,totalStudent,totalInstructor);
        return coachingDto;
    }


}
