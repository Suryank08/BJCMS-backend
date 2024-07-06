package com.cms.bjm_cms.service.course;

import com.cms.bjm_cms.dao.course.CourseDao;
import com.cms.bjm_cms.entity.course.Course;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseServiceImpl implements CourseService {
    private CourseDao courseDao;

    @Autowired
    public CourseServiceImpl(CourseDao courseDao) {
        this.courseDao = courseDao;
    }

    @Transactional
    public Course addCourse(Course course) {
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



