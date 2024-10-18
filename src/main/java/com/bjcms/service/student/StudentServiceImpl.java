package com.bjcms.service.student;


import com.bjcms.dao.course.CourseDao;
import com.bjcms.dao.student.StudentDao;
import com.bjcms.dto.Student.StudentDto;
import com.bjcms.entity.coaching.Coaching;
import com.bjcms.entity.course.Course;
import com.bjcms.entity.course.offline.Batch;
import com.bjcms.entity.instructor.Instructor;
import com.bjcms.entity.student.Student;
import com.bjcms.service.coaching.CoachingService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class StudentServiceImpl implements StudentService {
    StudentDao studentDao;
    CourseDao courseDao;
    CoachingService coachingService;

    @Autowired
    public StudentServiceImpl(StudentDao studentDao, CourseDao courseDao, CoachingService coachingService) {
        this.studentDao = studentDao;
        this.courseDao = courseDao;
        this.coachingService = coachingService;
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

    public List<StudentDto> courseEnrolledStudent(Integer courseId) {
        List<Student> studentList = null;
        try {

            Course course = courseDao.findById(courseId).orElseThrow(() -> new IllegalArgumentException("course id is not present"));
            if (course.getCourseType().getCourseTypeName().equals("online")) {
                studentList = course.getOnlineCourse().getStudentList();
            } else if (course.getCourseType().getCourseTypeName().equals("offline")) {
                studentList = course.getOfflineCourse().getBatchList().stream()
                        .flatMap(batch -> batch.getStudentList().stream())
                        .collect(Collectors.toList());
            }


        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        assert studentList != null;
        return convertStudentlistToStudentDtoList(studentList);
    }

    public List<StudentDto> getStudentsByCoachingId(Integer coachingId) {
        Coaching coaching = coachingService.findCoachingByCoachingId(coachingId);
        List<Student> studentList = coaching.getStudentList();

        List<StudentDto> studentDtoList = studentList.stream()
                .map(student -> {
                    String fullName = student.getFirstName() + " " + student.getLastName();
                    List<String> instructorNames = student.getBatchList().stream()
                            .flatMap(batch -> batch.getOfflineCourse().getCourse().getInstructorList().stream())
                            .map(Instructor::getInstructorName)
                            .collect(Collectors.toList());

                    List<String> courseNames = Stream.concat(
                            student.getOnlineCourseList().stream()
                                    .map(onlineCourse -> onlineCourse.getCourse().getCourseName()),
                            student.getBatchList().stream()
                                    .map(batch -> batch.getOfflineCourse().getCourse().getCourseName())
                    ).collect(Collectors.toList());

                    List<String> batchNames = student.getBatchList().stream()
                            .map(Batch::getBatchName)
                            .collect(Collectors.toList());

                    return new StudentDto(
                            student.getStudentId(),
                            fullName,
                            student.getEmail(),
                            student.getMobileNumber(),
                            instructorNames,
                            courseNames,
                            batchNames
                    );
                })
                .toList();

        return studentDtoList;
    }
    public List<StudentDto> convertStudentlistToStudentDtoList(List<Student> studentList){
        List<StudentDto> studentDtoList = studentList.stream()
                .map(student -> {
                    String fullName = student.getFirstName() + " " + student.getLastName();
                    List<String> instructorNames = student.getBatchList().stream()
                            .flatMap(batch -> batch.getOfflineCourse().getCourse().getInstructorList().stream())
                            .map(Instructor::getInstructorName)
                            .collect(Collectors.toList());

                    List<String> courseNames = Stream.concat(
                            student.getOnlineCourseList().stream()
                                    .map(onlineCourse -> onlineCourse.getCourse().getCourseName()),
                            student.getBatchList().stream()
                                    .map(batch -> batch.getOfflineCourse().getCourse().getCourseName())
                    ).collect(Collectors.toList());

                    List<String> batchNames = student.getBatchList().stream()
                            .map(Batch::getBatchName)
                            .collect(Collectors.toList());

                    return new StudentDto(
                            student.getStudentId(),
                            fullName,
                            student.getEmail(),
                            student.getMobileNumber(),
                            instructorNames,
                            courseNames,
                            batchNames
                    );
                })
                .toList();
        return studentDtoList;
    }

}
