package com.bjcms.service.student;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bjcms.dao.student.StudentDao;
import com.bjcms.entity.student.Student;

import jakarta.transaction.Transactional;

@Service
public class StudentServiceImpl implements StudentService {
    StudentDao studentDao;

    @Autowired
    public StudentServiceImpl(StudentDao studentDao) {
        this.studentDao = studentDao;
    }



    @Transactional
    public List<Student> addStudent(List<Student> studentList) {
       for(Student student:studentList){
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

}
