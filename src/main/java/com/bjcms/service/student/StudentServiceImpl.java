package com.bjcms.service.student;


import com.bjcms.dao.course.CourseDao;
import com.bjcms.dao.student.StudentDao;
import com.bjcms.entity.course.Course;
import com.bjcms.entity.student.Student;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {
    StudentDao studentDao;
    CourseDao courseDao;

    @Autowired
    public StudentServiceImpl(StudentDao studentDao, CourseDao courseDao) {
        this.studentDao = studentDao;
        this.courseDao = courseDao;
    }


    @Transactional
    public List<Student> addStudent(List<Student> studentList) {
        for (Student student : studentList) {
            studentDao.save(student);
        }
        return studentDao.findAll();
    }

    @Transactional
    public Student updateStudent(Student student) {
        return studentDao.save(student);
    }

    @Transactional
    public void deleteStudent(int id) {
        Optional<Student> optionalStudent = studentDao.findById(id);
        Student student = optionalStudent.orElse(new Student());
        studentDao.delete(student);
    }

    public List<Student> getAllStudent() {
        List<Student> studentList = studentDao.findAll();
        return studentList;
    }


    public Student findStudent(int id) {
        Optional<Student> optionalStudent = studentDao.findById(id);
        Student student = optionalStudent.orElse(new Student());
        return student;
    }

    public List<Student> courseEnrolledStudent(Integer courseId) {
        List<Student> studentList=null;
        try {

            Course course = courseDao.findById(courseId).orElseThrow(() -> new IllegalArgumentException("course id is not present"));
            if (course.getCourseType().getCourseTypeName().equals("online")){
               studentList= course.getOnlineCourse().getStudentList();
            }
            else if (course.getCourseType().getCourseTypeName().equals("offline")) {
                studentList = course.getOfflineCourse().getBatchList().stream()
                        .flatMap(batch -> batch.getStudentList().stream())
                        .collect(Collectors.toList());
            }


        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return studentList;
    }

}
