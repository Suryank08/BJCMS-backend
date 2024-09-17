package com.bjcms.service.student;



import java.util.List;

import com.bjcms.entity.student.Student;

public interface StudentService {
    public List<Student> addStudent(List<Student> studentList);

    public void deleteStudent(int id);

    public Student updateStudent(Student student);

    public Student findStudent(int id);

    public List<Student> getAllStudent();
    public List<Student> courseEnrolledStudent(Integer courseId);

}
