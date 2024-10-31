package com.bjcms.service.course.offline;

import com.bjcms.dao.course.AttendanceDateDao;
import com.bjcms.dao.course.offline.OfflineCourseAttendenceDao;
import com.bjcms.dao.course.online.OnlineCourseAttendenceDao;
import com.bjcms.entity.course.AttendanceDateEntry;
import com.bjcms.entity.course.Course;
import com.bjcms.entity.course.offline.OfflineCourseAttendance;
import com.bjcms.entity.student.Student;
import com.bjcms.responses.AttendanceRequest;
import com.bjcms.service.Email.EmailSenderService;
import com.bjcms.service.course.CourseService;
import com.bjcms.service.student.StudentService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class OfflineCourseAttendanceServiceImpl implements OfflineCourseAttendanceService {

    private OfflineCourseAttendenceDao offlineCourseAttendenceDao;
    private CourseService courseService;
    private AttendanceDateDao attendanceDateDao;
    private StudentService studentService;
    private EmailSenderService emailSenderService;
@Autowired
    public OfflineCourseAttendanceServiceImpl(OfflineCourseAttendenceDao offlineCourseAttendenceDao, CourseService courseService, AttendanceDateDao attendanceDateDao, StudentService studentService, EmailSenderService emailSenderService) {
        this.offlineCourseAttendenceDao = offlineCourseAttendenceDao;
        this.courseService = courseService;
        this.attendanceDateDao = attendanceDateDao;
        this.studentService = studentService;
        this.emailSenderService = emailSenderService;
    }

    @Transactional
    public void addAttendance(AttendanceRequest attendanceRequest) {
       Integer courseId = attendanceRequest.getCourseId();
       Integer subjectId = attendanceRequest.getSubjectId();
       Integer batchId=attendanceRequest.getBatchId();
       Course course=courseService.findCourse(courseId);
       if (course == null) {
           throw new IllegalArgumentException("Course not found with courseId: " + courseId);
       }

       // Get or create the attendance date
       AttendanceDateEntry savedAttendanceDate = attendanceDateDao.findByDate(LocalDate.now())
               .orElseGet(() -> {
                   AttendanceDateEntry newAttendanceDateEntry = new AttendanceDateEntry(LocalDate.now());
                   return attendanceDateDao.save(newAttendanceDateEntry);
               });

       List<AttendanceDateEntry> attendanceDateEntryList = new ArrayList<>();
       attendanceDateEntryList.add(savedAttendanceDate);
       
       for (Integer studentId : attendanceRequest.getAbsentIds()) {
           Student student=studentService.findStudent(studentId);
           if (student == null) {
               throw new IllegalArgumentException("Student not found with studentId: " + studentId);
           }
           String email=  student.getEmail();
           emailSenderService.absentStudentEmail(email,course,student,batchId);
           
       }
       
       
       for (Integer studentId : attendanceRequest.getPresentIds()) {
           Optional<OfflineCourseAttendance> offlineCourseAttendenceOptional =
                   offlineCourseAttendenceDao.findOfflineCourseAttendence(courseId, subjectId, studentId,batchId);

           OfflineCourseAttendance offlineCourseAttendance;

           if (offlineCourseAttendenceOptional.isPresent()) {
               offlineCourseAttendance = offlineCourseAttendenceOptional.get();
               if(offlineCourseAttendance.getAttendanceDateList().stream().noneMatch(item->item.getAttendanceDate().equals(LocalDate.now()))) {
                   if (offlineCourseAttendance.getAttendanceDateList() == null) {
                       offlineCourseAttendance.setAttendanceDateList(new ArrayList<>());
                   }
                   offlineCourseAttendance.setAttendanceCount(offlineCourseAttendance.getAttendanceCount() + 1);
                   offlineCourseAttendance.getAttendanceDateList().addAll(attendanceDateEntryList);
               }
           } else {
               offlineCourseAttendance = new OfflineCourseAttendance();
               offlineCourseAttendance.setCourseId(courseId);
               offlineCourseAttendance.setSubjectId(subjectId);
               offlineCourseAttendance.setStudentId(studentId);
               offlineCourseAttendance.setBatchId(batchId);
               offlineCourseAttendance.setAttendanceCount(1);
               if (offlineCourseAttendance.getAttendanceDateList() == null) {
                   offlineCourseAttendance.setAttendanceDateList(new ArrayList<>());
               }
               offlineCourseAttendance.getAttendanceDateList().addAll(attendanceDateEntryList);
           }
           offlineCourseAttendenceDao.save(offlineCourseAttendance);
       }


   }
}
