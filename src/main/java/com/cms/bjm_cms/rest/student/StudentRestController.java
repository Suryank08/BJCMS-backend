package com.cms.bjm_cms.rest.student;

import com.cms.bjm_cms.entity.student.Student;
import com.cms.bjm_cms.service.student.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class StudentRestController {
  private StudentService studentService;

    @Autowired
    public StudentRestController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/students")
    public List<Student> getAllStudent(){
        return studentService.getAllStudent();
    }
    @GetMapping("/students/{studentId}")
    public Student getStudent(@PathVariable int studentId){
        return studentService.findStudent(studentId);
    }
    @PostMapping("/students")
    public List<Student> addStudent(@RequestBody List<Student> studentList){
        System.out.println("Added Student");
        return studentService.addStudent(studentList);

    }
    @DeleteMapping("/students/{studentId}")
    public void deleteStudent(@PathVariable int studentId){
         studentService.deleteStudent(studentId);
        System.out.println("Student Deleted");
    }
    @PutMapping("/students")
    public Student updateStudent(@RequestBody Student student){
       return studentService.updateStudent(student);
    }
}
