package com.bjcms.service.student;


import com.bjcms.dto.Student.StudentDto;
import com.bjcms.entity.student.Student;

import java.util.List;

public interface StudentService {
    public List<Student> addStudent(List<Student> studentList);

    public void deleteStudent(int id);

    public Student updateStudent(Student student);

    public Student findStudent(int id);

    public List<Student> getAllStudent();
    public List<StudentDto> courseEnrolledStudent(Integer courseId);
    public List<StudentDto> getStudentsByCoachingId(Integer coachingId);
    public List<StudentDto> convertStudentlistToStudentDtoList(List<Student> studentList);
}
