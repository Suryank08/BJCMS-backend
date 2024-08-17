package com.bjcms.service.course;

import java.util.List;
import java.util.Optional;

import com.bjcms.dao.course.CourseTypeDao;
import com.bjcms.entity.course.CourseType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bjcms.dao.course.CourseDao;
import com.bjcms.entity.course.Course;

import jakarta.transaction.Transactional;

@Service
public class CourseServiceImpl implements CourseService {
    private CourseDao courseDao;
    private CourseTypeDao courseTypeDao;

    @Autowired
    public CourseServiceImpl(CourseDao courseDao,CourseTypeDao courseTypeDao) {
        this.courseDao = courseDao;
        this.courseTypeDao =courseTypeDao;
    }

    @Transactional
    public Course addCourse(Course course) {
        CourseType courseType = course.getCourseType();
        Optional<CourseType> existingCourseType = courseTypeDao.findByCourseTypeName(courseType.getCourseTypeName());

        if (existingCourseType.isPresent()) {
            // Use the existing CourseType
            course.setCourseType(existingCourseType.get());
        } else {
            // Save the new CourseType
            courseType = courseTypeDao.save(courseType);
            course.setCourseType(courseType);
        }
        return courseDao.save(course);
    }

    @Transactional
    public List<Course> addCourses(List<Course> courseList) {
        for (Course course : courseList) {
            courseDao.save(course);
        }
        return courseDao.findAll();
    }

    @Transactional
    public Course updateCourse(Course course) {
        return courseDao.save(course);
    }

    @Transactional
    public void deleteCourse(int id) {
        Optional<Course> optionalCourse = courseDao.findById(id);
        Course course = optionalCourse.orElse(new Course());
        courseDao.delete(course);
    }

    public List<Course> getAllCourse() {
        List<Course> courseList = courseDao.findAll();
        return courseList;
    }


    public Course findCourse(int id) {
        Optional<Course> optionalCourse = courseDao.findById(id);
        Course course = optionalCourse.orElse(new Course());
        return course;
    }
    public Optional<Course> findByCourseId(Integer id){
        return courseDao.findById(id);
    }

}



