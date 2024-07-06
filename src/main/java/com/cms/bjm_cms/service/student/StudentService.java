package com.cms.bjm_cms.service.student;



import com.cms.bjm_cms.entity.student.Student;

import java.util.List;

public interface StudentService {
    public List<Student> addStudent(List<Student> studentList);

    public void deleteStudent(int id);

    public Student updateStudent(Student student);

    public Student findStudent(int id);

    public List<Student> getAllStudent();

}
