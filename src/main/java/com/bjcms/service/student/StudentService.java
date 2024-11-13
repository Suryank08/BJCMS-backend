package com.bjcms.service.student;


import com.bjcms.dto.Student.StudentDetailDto;
import com.bjcms.dto.Student.StudentSummaryDto;
import com.bjcms.entity.student.Student;

import java.util.List;

public interface StudentService {
    public List<Student> addStudent(List<Student> studentList);

    public void deleteStudent(int id);

    public Student updateStudent(Student student);

    public StudentDetailDto findStudentDetailsByCoachingId(int id, int coachingtId);
    public Student findStudent(Integer id);
    public List<Student> getAllStudent();
    public List<StudentSummaryDto> courseEnrolledStudent(Integer courseId);
    public List<StudentSummaryDto> getStudentsByCoachingId(Integer coachingId);
    public List<StudentDetailDto> convertStudentlistToStudentDtoList(List<Student> studentList);
}
