package com.bjcms.service.course.offline;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bjcms.dao.course.offline.OfflineCourseDao;
import com.bjcms.entity.course.offline.OfflineCourse;

import jakarta.transaction.Transactional;
@Service
public class OfflineCourseServiceImpl implements OfflineCourseService {
    OfflineCourseDao offlineCourseDao;

    @Autowired
    public OfflineCourseServiceImpl(OfflineCourseDao offlineCourseDao) {
        this.offlineCourseDao = offlineCourseDao;
    }

    @Transactional
    public List<OfflineCourse> addOfflineCourses(List<OfflineCourse> offlineCourseList) {
        for (OfflineCourse offlineCourse : offlineCourseList) {
            offlineCourseDao.save(offlineCourse);
        }
        return offlineCourseDao.findAll();
    }

    @Transactional
    public OfflineCourse updateOfflineCourse(OfflineCourse offlineCourse) {
        return offlineCourseDao.save(offlineCourse);
    }

    @Transactional
    public void deleteOfflineCourse(int id) {
        Optional<OfflineCourse> optionalOfflineCourse = offlineCourseDao.findById(id);
        OfflineCourse offlineCourse = optionalOfflineCourse.orElse(new OfflineCourse());
        offlineCourseDao.delete(offlineCourse);
    }

    public List<OfflineCourse> getAllOfflineCourses() {
        List<OfflineCourse> offlineCourseList = offlineCourseDao.findAll();
        return offlineCourseList;
    }

    public OfflineCourse findOfflineCourse(int id) {
        Optional<OfflineCourse> optionalOfflineCourse = offlineCourseDao.findById(id);
        OfflineCourse offlineCourse = optionalOfflineCourse.orElse(new OfflineCourse());
        return offlineCourse;
    }
}

