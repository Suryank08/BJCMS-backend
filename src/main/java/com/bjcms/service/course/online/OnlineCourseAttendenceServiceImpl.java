package com.bjcms.service.course.online;

import com.bjcms.dao.course.AttendanceDateDao;
import com.bjcms.dao.course.online.OnlineCourseAttendenceDao;
import com.bjcms.dao.student.StudentDao;
import com.bjcms.entity.course.AttendanceDateEntry;
import com.bjcms.entity.course.Course;
import com.bjcms.entity.course.online.OnlineCourseAttendance;
import com.bjcms.entity.instructor.Instructor;
import com.bjcms.entity.student.Student;
import com.bjcms.responses.AttendanceRequest;
import com.bjcms.service.Email.EmailSenderService;
import com.bjcms.service.course.CourseService;
import com.bjcms.service.student.StudentService;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OnlineCourseAttendenceServiceImpl implements OnlineCourseAttendenceService {
    private static final Logger log = LoggerFactory.getLogger(OnlineCourseAttendenceServiceImpl.class);
    private OnlineCourseAttendenceDao onlineCourseAttendenceDao;
    private CourseService courseService;
    private AttendanceDateDao attendanceDateDao;
    private StudentService studentService;
    private EmailSenderService emailSenderService;

    @Autowired
    public OnlineCourseAttendenceServiceImpl(OnlineCourseAttendenceDao onlineCourseAttendenceDao,EmailSenderService emailSenderService, CourseService courseService,StudentService studentService, AttendanceDateDao attendanceDateDao) {
        this.onlineCourseAttendenceDao = onlineCourseAttendenceDao;
        this.courseService = courseService;
        this.attendanceDateDao = attendanceDateDao;
        this.studentService=studentService;
        this.emailSenderService=emailSenderService;
    }

//    @Transactional
//    public void addAttendence(AttendanceRequest attendenceRequest) {
//        Integer courseId = attendenceRequest.getCourseId();
//        Integer subjectId = attendenceRequest.getSubjectId();
//        Integer onlineCourseId = courseService.findCourse(courseId).getOnlineCourse().getOnlineCourseId();
//        log.info("OnlineCourseId value:", onlineCourseId);
//        log.info("Searching for attendance on: {}", LocalDate.now());
//
//        OnlineCourseAttendance savedOnlineCourseAttendance = null;
////        AttendanceDateEntry savedAttendanceDate = null;
//        System.out.println(LocalDate.now());
////        Optional<AttendanceDateEntry> attendanceDateOptional = attendanceDateDao.findByDate(LocalDate.now());
////        if (attendanceDateOptional.isPresent()) {
////            System.out.println("hdsjgcjhisadguhlfg");
////            savedAttendanceDate = attendanceDateOptional.get();
////        } else {
////            AttendanceDateEntry attendanceDateEntry = new AttendanceDateEntry(LocalDate.now());
////            savedAttendanceDate = attendanceDateDao.save(attendanceDateEntry);
////        }
//        AttendanceDateEntry savedAttendanceDate = attendanceDateDao.findByDate(LocalDate.now())
//                .orElseGet(() -> {
//                    AttendanceDateEntry newAttendanceDateEntry = new AttendanceDateEntry(LocalDate.now());
//                    return attendanceDateDao.save(newAttendanceDateEntry);
//                });
//
//        List<AttendanceDateEntry> attendanceDateEntryList = new ArrayList<>();
//        attendanceDateEntryList.add(savedAttendanceDate);
//        for (Integer studentId : attendenceRequest.getPresentIds()) {
//            Optional<OnlineCourseAttendance> onlineCourseAttendenceOptional = onlineCourseAttendenceDao.findOnlineCourseAttendence(onlineCourseId, subjectId, studentId);
//            if (onlineCourseAttendenceOptional.isPresent()) {
//                onlineCourseAttendenceOptional.get().setAttendenceCount(onlineCourseAttendenceOptional.get().getAttendenceCount() + 1);
//                savedOnlineCourseAttendance = onlineCourseAttendenceDao.save(onlineCourseAttendenceOptional.get());
//            } else {
//                OnlineCourseAttendance onlineCourseAttendance = new OnlineCourseAttendance();
//                onlineCourseAttendance.setOnlineCourseId(onlineCourseId);
//                onlineCourseAttendance.setSubjectId(subjectId);
//                onlineCourseAttendance.setStudentId(studentId);
//                onlineCourseAttendance.setAttendenceCount(1);
//                savedOnlineCourseAttendance = onlineCourseAttendenceDao.save(onlineCourseAttendance);
//            }
//            System.out.println(savedOnlineCourseAttendance.toString());
//            if (savedOnlineCourseAttendance != null) {
//                if (savedOnlineCourseAttendance.getAttendanceDateList() != null)
//                    attendanceDateEntryList.addAll(savedOnlineCourseAttendance.getAttendanceDateList());
//                savedOnlineCourseAttendance.setAttendanceDateList(attendanceDateEntryList);
//                onlineCourseAttendenceDao.save(savedOnlineCourseAttendance);
//            }
//        }
//
//    }
@Transactional
public void addAttendence(AttendanceRequest attendenceRequest) {
    Integer courseId = attendenceRequest.getCourseId();
    Integer subjectId = attendenceRequest.getSubjectId();
    Course course=courseService.findCourse(courseId);
    if (course == null) {
        throw new IllegalArgumentException("Course not found with courseId: " + courseId);
    }
    Integer onlineCourseId = course.getOnlineCourse().getOnlineCourseId();

    log.info("OnlineCourseId value: {}", onlineCourseId);
    log.info("Searching for attendance on: {}", LocalDate.now());

    // Get or create the attendance date
    AttendanceDateEntry savedAttendanceDate = attendanceDateDao.findByDate(LocalDate.now())
            .orElseGet(() -> {
                AttendanceDateEntry newAttendanceDateEntry = new AttendanceDateEntry(LocalDate.now());
                return attendanceDateDao.save(newAttendanceDateEntry);
            });

    List<AttendanceDateEntry> attendanceDateEntryList = new ArrayList<>();
    attendanceDateEntryList.add(savedAttendanceDate);

    //for Absent student Sending Mail
        for (Integer studentId : attendenceRequest.getAbsentIds()) {
            Student student=studentService.findStudent(studentId);
            if (student == null) {
                throw new IllegalArgumentException("Student not found with studentId: " + studentId);
            }
          String email=  student.getEmail();
          String subject= "Regarding Absent in Course of ".concat(course.getCourseName());
            StringBuffer sb = new StringBuffer();
            sb.append(student.getFirstName());
            sb.append(" ");
            sb.append(student.getLastName());
            sb.append(" is Absent on ");
            sb.append(LocalDate.now(ZoneId.of("Asia/Kolkata")).toString());
            sb.append(" in Course on ");
            sb.append(course.getCourseName());
            sb.append(System.lineSeparator());
            sb.append("By:- ");
            sb.append(course.getInstructorList().stream()
                    .map(Instructor::getInstructorName)
                    .filter(Objects::nonNull)  // Optional: to handle null values
                    .collect(Collectors.joining(", ")));

            String body = sb.toString();
            emailSenderService.sendEmail(email,subject,body);
        }


    // Iterate over Present students and update attendance
    for (Integer studentId : attendenceRequest.getPresentIds()) {
        Optional<OnlineCourseAttendance> onlineCourseAttendenceOptional =
                onlineCourseAttendenceDao.findOnlineCourseAttendence(onlineCourseId, subjectId, studentId);

        OnlineCourseAttendance onlineCourseAttendance;

        if (onlineCourseAttendenceOptional.isPresent()) {
            onlineCourseAttendance = onlineCourseAttendenceOptional.get();
            if(onlineCourseAttendance.getAttendanceDateList().stream().noneMatch(item->item.getAttendanceDate().equals(LocalDate.now()))) {
                if (onlineCourseAttendance.getAttendanceDateList() == null) {
                    onlineCourseAttendance.setAttendanceDateList(new ArrayList<>());
                }
                onlineCourseAttendance.setAttendenceCount(onlineCourseAttendance.getAttendenceCount() + 1);
                onlineCourseAttendance.getAttendanceDateList().addAll(attendanceDateEntryList);
            }
            else{
                log.info(onlineCourseAttendance.getAttendanceId()+"Already Present");
            }
        } else {
            onlineCourseAttendance = new OnlineCourseAttendance();
            onlineCourseAttendance.setOnlineCourseId(onlineCourseId);
            onlineCourseAttendance.setSubjectId(subjectId);
            onlineCourseAttendance.setStudentId(studentId);
            onlineCourseAttendance.setAttendenceCount(1);
            if (onlineCourseAttendance.getAttendanceDateList() == null) {
                onlineCourseAttendance.setAttendanceDateList(new ArrayList<>());
            }
            onlineCourseAttendance.getAttendanceDateList().addAll(attendanceDateEntryList);
        }

        // Handle attendance date list
//        if (onlineCourseAttendance.getAttendanceDateList() == null) {
//            onlineCourseAttendance.setAttendanceDateList(new ArrayList<>());
//        }

        // Update the attendance date list
//        onlineCourseAttendance.getAttendanceDateList().addAll(attendanceDateEntryList);

        // Save the attendance record
        onlineCourseAttendenceDao.save(onlineCourseAttendance);
    }
}



}
