package com.bjcms.service.course;

import com.bjcms.dao.course.CourseTypeDao;
import com.bjcms.entity.course.CourseType;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class CourseTypeServiceImpl implements CourseTypeService {
    CourseTypeDao courseTypeDao;

    @Autowired
    public CourseTypeServiceImpl(CourseTypeDao courseTypeDao) {
        this.courseTypeDao = courseTypeDao;
    }

    @Transactional
    public List<CourseType> addCourseType(List<CourseType> courseTypeList) {
        for (CourseType courseType : courseTypeList) {
            courseTypeDao.save(courseType);
        }
        return courseTypeDao.findAll();
    }

    @Transactional
    public CourseType updateCourseType(CourseType courseType) {
        return courseTypeDao.save(courseType);
    }

    @Transactional
    public void deleteCourseType(int id) {
        Optional<CourseType> optionalCourseType = courseTypeDao.findById(id);
        CourseType courseType = optionalCourseType.orElse(new CourseType());
        courseTypeDao.delete(courseType);
    }

    public List<CourseType> getAllCourseTypes() {
        List<CourseType> courseTypeList = courseTypeDao.findAll();
        return courseTypeList;
    }

    public CourseType findCourseType(int id) {
        Optional<CourseType> optionalCourseType = courseTypeDao.findById(id);
        CourseType courseType = optionalCourseType.orElse(new CourseType());
        return courseType;
    }
}

