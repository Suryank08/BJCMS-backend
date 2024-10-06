package com.bjcms.rest.student;

import com.bjcms.dto.StudentDto.StudentDto;
import com.bjcms.dto.instructor.InstructorDto;
import com.bjcms.entity.student.Student;
import com.bjcms.service.student.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentRestController  {
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

    @GetMapping("/coachingStudents")
    @PreAuthorize("hasAnyAuthority('ADMIN','CO-ADMIN')")
    public ResponseEntity<List<StudentDto>> getStudentsByCoachingId(@RequestParam Integer coachingId) {
        // Log coachingId for debugging
        System.out.println("coachingId: " + coachingId);

        List<StudentDto> studentList = studentService.getStudentsByCoachingId(coachingId);
        if (studentList != null && !studentList.isEmpty()) {
            return ResponseEntity.ok(studentList);
        } else {
            return ResponseEntity.noContent().build();  // Return 204 if no instructors found
        }
    }
    @PostMapping("/create")
    public List<Student> addStudent(@RequestBody List<Student> studentList){
        System.out.println("Added Student");
        return studentService.addStudent(studentList);
    }

    @PostMapping("/courseEnrolledStudent")
    public List<Student> courseEnrolledStudent(@RequestBody Integer courseId){
        return studentService.courseEnrolledStudent(courseId);
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
