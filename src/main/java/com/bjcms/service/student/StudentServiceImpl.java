package com.bjcms.service.student;


import com.bjcms.dao.course.CourseDao;
import com.bjcms.dao.student.StudentDao;
import com.bjcms.dto.Student.StudentDetailDto;
import com.bjcms.dto.Student.StudentSummaryDto;
import com.bjcms.entity.coaching.Coaching;
import com.bjcms.entity.course.Course;
import com.bjcms.entity.course.offline.Batch;
import com.bjcms.entity.instructor.Instructor;
import com.bjcms.entity.student.Student;
import com.bjcms.service.coaching.CoachingService;
import com.bjcms.service.course.CourseService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class StudentServiceImpl implements StudentService {
    StudentDao studentDao;
    CourseDao courseDao;
    CoachingService coachingService;
    CourseService courseService;

    @Autowired
    public StudentServiceImpl(StudentDao studentDao, CourseDao courseDao, CoachingService coachingService, CourseService courseService) {
        this.studentDao = studentDao;
        this.courseDao = courseDao;
        this.coachingService = coachingService;
        this.courseService=courseService;
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
//    public StudentDetailDto(Integer studentId, String studentName, String email, String mobileNumber, List<String> instructorName, List<String> enrolledCourseList, List<String> batchDetails) {


//        public StudentDetailDto findStudentDetailsByCoachingId(int id, int coachingId) {
//       Student student = studentDao.findById(id).orElseThrow(()->new IllegalArgumentException("No Student Found with this id"));
//       String studentName=student.getFirstName()+" "+student.getLastName();
//            List<Course> coachingCourseList = courseService.getAllCourseByCoachingId(coachingId);
//            Set<Integer> coachingCourseIds = coachingCourseList.stream()
//                    .map(Course::getCourseId) // Extract courseId from Course
//                    .collect(Collectors.toSet());
//
//            Set<Course> uniqueOnlineCoursesSet = student.getOnlineCourseList().stream()
//                    .map(onlineCourse -> onlineCourse.getCourse()) // Map to Course
//                    .filter(course -> coachingCourseIds.contains(course.getCourseId())) // Filter to include only matching courses
//                    .collect(Collectors.toSet());
//
//            Set<Course> uniqueOfflineCoursesSet = student.getBatchList().stream().map(batch -> batch.getOfflineCourse().getCourse())// Map to Course
//                    .filter(course -> coachingCourseIds.contains(course.getCourseId())) // Filter to include only matching courses
//                    .collect(Collectors.toSet());
//            List<Course> uniqueOnlineCourseList=new ArrayList<>(uniqueOnlineCoursesSet);
//            List<Course> uniqueOfflineCourseList=new ArrayList<>(uniqueOnlineCoursesSet);
//            List<Course> uniqueCourses =uniqueOnlineCourseList.addAll(uniqueOfflineCourseList);
//            List<String> studentInstructorList = uniqueCourses.stream().flatMap(course->course.getInstructorList().stream().map(Instructor::getInstructorName)).toList();
//            List<String> batchDetailsList = uniqueOfflineCourseList.stream()// Filter to only include offline courses
//                    .flatMap(course -> course.getOfflineCourse().getBatchList().stream().map(Batch::getBatchName)) // Map to batch names
//                    .toList(); // Collect to list
//            List<String> courseNameList=uniqueCourses.stream().map(Course::getCourseName).toList();
//            StudentDetailDto studentDetailDto = new StudentDetailDto(student.getStudentId(),studentName,student.getEmail(),student.getMobileNumber(),studentInstructorList,courseNameList,batchDetailsList );
//        return studentDetailDto;
//    }

    public StudentDetailDto findStudentDetailsByCoachingId(int id, int coachingId) {
        // Fetch the student or throw an exception if not found
        Student student = studentDao.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("No Student Found with this id"));

        // Build the student's full name
        String studentName = student.getFirstName() + " " + student.getLastName();

        // Get the list of courses available in the coaching by ID
        List<Course> coachingCourseList = courseService.getAllCourseByCoachingId(coachingId);

        // Extract the course IDs into a Set for easy comparison
        Set<Integer> coachingCourseIds = coachingCourseList.stream()
                .map(Course::getCourseId)
                .collect(Collectors.toSet());

        // Merge online and offline course streams
        Set<Course> uniqueCoursesSet = Stream.concat(
                        // Online courses
                        student.getOnlineCourseList().stream()
                                .map(onlineCourse -> onlineCourse.getCourse()), // Map to Course
                        // Offline courses (from batches)
                        student.getBatchList().stream()
                                .map(batch -> batch.getOfflineCourse().getCourse()) // Map to Course
                )
                // Filter courses that match coaching course IDs
                .filter(course -> coachingCourseIds.contains(course.getCourseId()))
                // Collect to a Set to ensure uniqueness
                .collect(Collectors.toSet());

        // Convert the set to a list
        List<Course> uniqueCourses = new ArrayList<>(uniqueCoursesSet);

        // Ensure unique instructors based on instructorId
        Set<Integer> uniqueInstructorIds = new HashSet<>();
        List<String> studentInstructorList = uniqueCourses.stream()
                .flatMap(course -> course.getInstructorList().stream())
                // Filter only unique instructors based on instructorId
                .filter(instructor -> uniqueInstructorIds.add(instructor.getInstructorId()))
                // Map to instructor names
                .map(Instructor::getInstructorName)
                .toList();

        // Extract batch details from offline courses
        List<String> batchDetailsList = uniqueCourses.stream()
                .filter(course -> course.getCourseType().getCourseTypeName().equalsIgnoreCase("offline")) // Filter to offline courses
                .flatMap(course -> course.getOfflineCourse().getBatchList().stream().map(Batch::getBatchName))
                .toList();

        // Extract course names from the unique courses
        List<String> courseNameList = uniqueCourses.stream()
                .map(Course::getCourseName)
                .toList();

        // Create and return the StudentDetailDto with the collected details
        StudentDetailDto studentDetailDto = new StudentDetailDto(
                student.getStudentId(),
                studentName,
                student.getEmail(),
                student.getMobileNumber(),
                studentInstructorList,
                courseNameList,
                batchDetailsList
        );

        return studentDetailDto;
    }


    public Student findStudent(Integer id){
        Student student= studentDao.findById(id).orElseThrow(()->new IllegalArgumentException("No Student Found With This id"));
        return student;
    }
    public List<StudentSummaryDto> courseEnrolledStudent(Integer courseId) {
        List<StudentSummaryDto> studentSummaryDtoList = null;
        try {
            List<Student> studentList = null;
            Course course = courseDao.findById(courseId).orElseThrow(() -> new IllegalArgumentException("course id is not present"));
            if (course.getCourseType().getCourseTypeName().equals("online")) {
                studentList = course.getOnlineCourse().getStudentList();
            } else if (course.getCourseType().getCourseTypeName().equals("offline")) {
                studentList = course.getOfflineCourse().getBatchList().stream()
                        .flatMap(batch -> batch.getStudentList().stream())
                        .collect(Collectors.toList());
            }

            assert studentList != null;
            studentSummaryDtoList = studentList.stream()  .map(student -> {
                String fullName = student.getFirstName() + " " + student.getLastName();
                return new StudentSummaryDto(
                        student.getStudentId(),
                        fullName,
                        student.getEmail(),
                        student.getMobileNumber(),
                        student.getBatchList().stream().map(Batch::getBatchId).toList()
                );
            }).toList();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        assert studentSummaryDtoList != null;
        return studentSummaryDtoList;
    }

    public List<StudentSummaryDto> getStudentsByCoachingId(Integer coachingId) {
        Coaching coaching = coachingService.findCoachingByCoachingId(coachingId);
        List<Student> studentList = coaching.getStudentList();

        List<StudentSummaryDto> studentDtoList = studentList.stream()
                .map(student -> {
                    String fullName = student.getFirstName() + " " + student.getLastName();
                    return new StudentSummaryDto(
                            student.getStudentId(),
                            fullName,
                            student.getEmail(),
                            student.getMobileNumber()
                    );
                })
                .toList();

        return studentDtoList;
    }
    public List<StudentDetailDto> convertStudentlistToStudentDtoList(List<Student> studentList){
        List<StudentDetailDto> studentDtoList = studentList.stream()
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

                    return new StudentDetailDto(
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
