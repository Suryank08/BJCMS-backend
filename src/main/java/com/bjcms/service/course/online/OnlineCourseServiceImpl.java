package com.bjcms.service.course.online;

import com.bjcms.dao.course.online.OnlineCourseDao;
import com.bjcms.entity.course.online.OnlineCourse;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class OnlineCourseServiceImpl implements OnlineCourseService {
    OnlineCourseDao onlineCourseDao;

    @Autowired
    public OnlineCourseServiceImpl(OnlineCourseDao onlineCourseDao) {
        this.onlineCourseDao = onlineCourseDao;
    }

    @Transactional
    public List<OnlineCourse> addOnlineCourses(List<OnlineCourse> onlineCourseList) {
        for (OnlineCourse onlineCourse : onlineCourseList) {
            onlineCourseDao.save(onlineCourse);
        }
        return onlineCourseDao.findAll();
    }

    @Transactional
    public OnlineCourse updateOnlineCourse(OnlineCourse onlineCourse) {
        return onlineCourseDao.save(onlineCourse);
    }

    @Transactional
    public void deleteOnlineCourse(int id) {
        Optional<OnlineCourse> optionalOnlineCourse = onlineCourseDao.findById(id);
        OnlineCourse onlineCourse = optionalOnlineCourse.orElse(new OnlineCourse());
        onlineCourseDao.delete(onlineCourse);
    }

    public List<OnlineCourse> getAllOnlineCourses() {
        List<OnlineCourse> onlineCourseList = onlineCourseDao.findAll();
        return onlineCourseList;
    }

    public OnlineCourse findOnlineCourse(int id) {
        Optional<OnlineCourse> optionalOnlineCourse = onlineCourseDao.findById(id);
        OnlineCourse onlineCourse = optionalOnlineCourse.orElse(new OnlineCourse());
        return onlineCourse;
    }
}
