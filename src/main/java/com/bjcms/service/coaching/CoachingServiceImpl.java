package com.bjcms.service.coaching;

import com.bjcms.dao.coaching.CoachingAdminDao;
import com.bjcms.dao.coaching.CoachingDao;
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

    public Coaching getCoachingOfCoachingAdmin(String email) {
        try {
            CoachingAdmin coachingAdmin = coachingAdminDao.findByCoachingAdminEmail(email).orElseThrow(() -> new IllegalArgumentException("Coaching Admin Do not Exist"));
        return coachingAdmin.getCoaching();
        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }


}
