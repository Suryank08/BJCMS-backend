package com.bjcms.service.course;

import com.bjcms.dao.course.CourseDao;
import com.bjcms.dao.course.CourseTypeDao;
import com.bjcms.dao.student.StudentDao;
import com.bjcms.dao.user.RoleDao;
import com.bjcms.dao.user.UserDao;
import com.bjcms.entity.course.Course;
import com.bjcms.entity.course.CourseType;
import com.bjcms.entity.student.Student;
import com.bjcms.entity.user.Role;
import com.bjcms.entity.user.User;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseServiceImpl implements CourseService {
    private CourseDao courseDao;
    private CourseTypeDao courseTypeDao;
    private UserDao userDao;
    private StudentDao studentDao;
    private RoleDao roleDao;

    @Autowired
    public CourseServiceImpl(CourseDao courseDao, CourseTypeDao courseTypeDao, UserDao userDao, StudentDao studentDao, RoleDao roleDao) {
        this.courseDao = courseDao;
        this.courseTypeDao = courseTypeDao;
        this.userDao = userDao;
        this.studentDao = studentDao;
        this.roleDao = roleDao;
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

    public Optional<Course> findByCourseId(Integer id) {
        return courseDao.findById(id);
    }


    public Course enrollStudentInCourse(Integer courseId, String email) {

        try {
            Course course = courseDao.findById(courseId)
                    .orElseThrow(() -> new IllegalArgumentException("Something went wrong"));
            User user = userDao.findByEmail(email)
                    .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));
            Role studentRole = roleDao.findByRoleName("STUDENT")
                    .orElseThrow(() -> new IllegalArgumentException("Role 'STUDENT' not found"));

            Student student;
            if (user.getRoles().stream().noneMatch(role ->
                             "STUDENT".equals(role.getRoleName()) ||
                            "ADMIN".equals(role.getRoleName()) ||
                            "INSTRUCTOR".equals(role.getRoleName()))) {
                student = new Student();
                student.setFirstName(user.getFirstName());
                student.setLastName(user.getLastName());
                student.setMobileNumber(user.getMobileNumber());
                student.setEmail(user.getEmail());
                studentDao.save(student);
                user.getRoles().add(studentRole);
                userDao.save(user);
            } else if (user.getRoles().stream().anyMatch(role -> "STUDENT".equals(role.getRoleName()))) {
                student = studentDao.findByEmail(email)
                        .orElseThrow(() -> new UsernameNotFoundException("Student not found with email: " + email));
            } else {
                throw new IllegalStateException("User does not have the required role.");
            }

            String courseType = course.getCourseType().getCourseTypeName();
            if ("offline".equals(courseType)) {
                course.getOfflineCourse().getBatchList().forEach(batch -> batch.getStudentList().add(student));
            } else if ("online".equals(courseType)) {
                course.getOnlineCourse().getStudentList().add(student);
            } else {
                throw new IllegalArgumentException("Invalid course type.");
            }

            return courseDao.save(course);
        } catch (Exception e) {
            throw new RuntimeException("Failed to enroll student in course", e);
        }
    }

}



