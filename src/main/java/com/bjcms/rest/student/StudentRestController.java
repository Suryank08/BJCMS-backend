package com.bjcms.rest.student;

import java.security.Principal;
import java.util.List;

import com.bjcms.entity.course.Course;
import com.bjcms.responses.EnrollmentRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bjcms.entity.student.Student;
import com.bjcms.service.student.StudentService;

@RestController
@RequestMapping("/api/students")
public class StudentRestController {
  private StudentService studentService;

    @Autowired
    public StudentRestController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/")
    @PreAuthorize("hasAnyAuthority('ADMIN','INSTRUCTOR')")
    public List<Student> getAllStudent(){
        return studentService.getAllStudent();
    }

    @GetMapping("/{studentId}")
    @PreAuthorize("hasAnyAuthority('ADMIN' ,'INSTRUCTOR','STUDENT')")
    public Student getStudent(@PathVariable int studentId){
        return studentService.findStudent(studentId);
    }


    @PostMapping("/create")
    public List<Student> addStudent(@RequestBody List<Student> studentList){
        System.out.println("Added Student");
        return studentService.addStudent(studentList);

    }
    @DeleteMapping("/{studentId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public void deleteStudent(@PathVariable int studentId){
         studentService.deleteStudent(studentId);
        System.out.println("Student Deleted");
    }
    @PostMapping("/update")
    @PreAuthorize("hasAnyAuthority('ADMIN' ,'INSTRUCTOR','STUDENT')")
    public Student updateStudent(@RequestBody Student student){
       return studentService.updateStudent(student);
    }
}
