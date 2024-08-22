package com.bjcms.service.course;

import com.bjcms.dao.course.CourseDao;
import com.bjcms.dao.course.CourseTypeDao;
import com.bjcms.dao.course.SubjectDao;
import com.bjcms.dao.course.offline.BatchDao;
import com.bjcms.dao.course.offline.OfflineCourseDao;
import com.bjcms.dao.course.online.OnlineCourseDao;
import com.bjcms.dao.course.online.VideoDao;
import com.bjcms.dao.instructor.InstructorDao;
import com.bjcms.dao.student.StudentDao;
import com.bjcms.dao.user.RoleDao;
import com.bjcms.dao.user.UserDao;
import com.bjcms.entity.course.Course;
import com.bjcms.entity.course.CourseType;
import com.bjcms.entity.course.Subject;
import com.bjcms.entity.course.offline.Batch;
import com.bjcms.entity.course.offline.OfflineCourse;
import com.bjcms.entity.course.online.OnlineCourse;
import com.bjcms.entity.course.online.Video;
import com.bjcms.entity.instructor.Instructor;
import com.bjcms.entity.student.Student;
import com.bjcms.entity.user.Role;
import com.bjcms.entity.user.User;
import com.bjcms.service.course.offline.BatchService;
import jakarta.transaction.Transactional;
import org.aspectj.apache.bcel.generic.Instruction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CourseServiceImpl implements CourseService {
    private CourseDao courseDao;
    private CourseTypeDao courseTypeDao;
    private UserDao userDao;
    private StudentDao studentDao;
    private RoleDao roleDao;
    private OfflineCourseDao offlineCourseDao;
    private OnlineCourseDao onlineCourseDao;
    private SubjectDao subjectDao;
    private VideoDao videoDao;
    private InstructorDao instructorDao;
    private BatchDao batchDao;

    @Autowired
    public CourseServiceImpl(CourseDao courseDao,BatchDao batchDao,SubjectDao subjectDao,InstructorDao instructorDao, VideoDao videoDao,CourseTypeDao courseTypeDao, UserDao userDao, StudentDao studentDao, RoleDao roleDao,OnlineCourseDao onlineCourseDao,OfflineCourseDao offlineCourseDao) {
        this.courseDao = courseDao;
        this.courseTypeDao = courseTypeDao;
        this.userDao = userDao;
        this.roleDao = roleDao;
        this.offlineCourseDao=offlineCourseDao;
        this.onlineCourseDao=onlineCourseDao;
        this.subjectDao=subjectDao;
        this.studentDao=studentDao;
        this.videoDao=videoDao;
        this.instructorDao=instructorDao;
        this.batchDao=batchDao;
    }

@Transactional
public Course addCourse(Course course) {
    System.out.println("Starting addCourse method"+course);

    // Handling CourseType
    CourseType courseType = course.getCourseType();
    if (courseType != null && courseType.getCourseTypeName() != null && !courseType.getCourseTypeName().isEmpty()) {
        Optional<CourseType> existingCourseType = courseTypeDao.findByCourseTypeName(courseType.getCourseTypeName());
        if (existingCourseType.isPresent()) {
            course.setCourseType(existingCourseType.get());
        } else {
            courseType = courseTypeDao.save(courseType);
            course.setCourseType(courseType);
        }
    }

    // Save the Course entity first to generate an ID
    Course savedCourse = courseDao.save(course);
    System.out.println("Course saved with ID: " + savedCourse.getCourseId());

//     Handling OfflineCourse
    OfflineCourse offlineCourse = course.getOfflineCourse();
    OnlineCourse onlineCourse = course.getOnlineCourse();
    if (offlineCourse != null && offlineCourse.getStatus() != null && !offlineCourse.getStatus().isEmpty()) {
        System.out.println("OfflineCourse present");

        offlineCourse.setCourse(savedCourse);

        if(offlineCourse.getSubjectList()!=null&&!offlineCourse.getSubjectList().isEmpty()){
            System.out.println("inside Subject");
            List<Subject> subjectList =offlineCourse.getSubjectList();
            List<Subject> savedSubjects = subjectDao.saveAll(subjectList);
            offlineCourse.setSubjectList(savedSubjects);
        }
        if(offlineCourse.getBatchList()!=null&&!offlineCourse.getBatchList().isEmpty()){
            System.out.println("inside Batch");
            List<Batch> batchList =offlineCourse.getBatchList();
            List<Batch> savedBatch = batchDao.saveAll(batchList);
            offlineCourse.setBatchList(savedBatch);
        }

        // Link the OfflineCourse to the saved Course
        offlineCourseDao.save(offlineCourse);
        savedCourse.setOfflineCourse(offlineCourse);
    }
 else if (onlineCourse != null && onlineCourse.getStatus() != null && !onlineCourse.getStatus().isEmpty()) {
                onlineCourse.setCourse(savedCourse);
            System.out.println("OnlineCourse courseId: " + onlineCourse.getOnlineCourseId());

        if(onlineCourse.getSubjectList()!=null&&!onlineCourse.getSubjectList().isEmpty()){
            System.out.println("inside Subject");
            List<Subject> subjectList =onlineCourse.getSubjectList();
            List<Subject> savedSubjects = subjectDao.saveAll(subjectList);
            onlineCourse.setSubjectList(savedSubjects);
        }
        if(onlineCourse.getStudentList()!=null&&!onlineCourse.getStudentList().isEmpty()){
            System.out.println("inside Student");
            List<Student> studentList=onlineCourse.getStudentList();
            List<Student> savedStudentList=studentDao.saveAll(studentList);
            onlineCourse.setStudentList(savedStudentList);
        }
        if(onlineCourse.getVideoList()!=null&&!onlineCourse.getVideoList().isEmpty()){
            System.out.println("inside Video");
            List<Video> videoList=onlineCourse.getVideoList();
            List<Video> savedVideoList= videoDao.saveAll(videoList);
            onlineCourse.setVideoList(savedVideoList);
        }
        onlineCourseDao.save(onlineCourse);
            savedCourse.setOnlineCourse(onlineCourse);

    }
    List<Instructor> instructorList=course.getInstructorList();
    if (instructorList != null && !instructorList.isEmpty()) {
        System.out.println("inside Instructor");
        for (Instructor instructor : instructorList) {
            if (!instructor.getCourseList().contains(savedCourse)) {
                instructor.getCourseList().add(savedCourse);
            }
        }

        List<Instructor> instructors = instructorDao.saveAll(instructorList);
        savedCourse.setInstructorList(instructors);
    }



    Course finalSavedCourse = courseDao.save(savedCourse);
    return finalSavedCourse;
}

//@Transactional
//public Course addCourse(Course course) {
//    System.out.println("Why this  is rnning");
//    CourseType courseType = course.getCourseType();
//    OfflineCourse offlineCourse = course.getOfflineCourse();
//    OnlineCourse onlineCourse = course.getOnlineCourse();
//Course newCourse =new Course();
//    if (courseType != null && courseType.getCourseTypeName() != null && !courseType.getCourseTypeName().isEmpty()) {
//        Optional<CourseType> existingCourseType = courseTypeDao.findByCourseTypeName(courseType.getCourseTypeName());
//        if (existingCourseType.isPresent()) {
//            course.setCourseType(existingCourseType.get());
//        } else {
//            courseType = courseTypeDao.save(courseType);
//            course.setCourseType(courseType);
//            newCourse=courseDao.save(course);
//        }
//    }
//
//    if (offlineCourse != null && offlineCourse.getStatus() != null && !offlineCourse.getStatus().isEmpty()) {
//        System.out.println("offlineCourse Present");
//        offlineCourseDao.save(offlineCourse);
//        course.setOfflineCourse(offlineCourse);
//
//    }
//try {
//    if (onlineCourse != null && onlineCourse.getStatus() != null && !onlineCourse.getStatus().isEmpty()) {
//        System.out.println("onlineCoursePresent" + onlineCourse);
////        OnlineCourse newOnlineCourse = onlineCourseDao.save(onlineCourse);
//        System.out.println("Exit");
////        System.out.println("new" + newOnlineCourse);
//        newCourse.setOnlineCourse(onlineCourse);
//    }
//}catch (Exception e){
//    e.printStackTrace();
//}
//    System.out.println("priting setted course"+course);
//
//    System.out.println("Printing newCourse"+newCourse);
//    return courseDao.save(newCourse);
//}


    @Transactional
    public List<Course> addCourses(List<Course> courseList) {
        for (Course course : courseList) {
            courseDao.save(course);
        }
        return courseDao.findAll();
    }

    @Transactional
    public Course updateCourse(Course course) {
        return courseDao.save(course);
    }

    @Transactional
    public void deleteCourse(int id) {
        Optional<Course> optionalCourse = courseDao.findById(id);
        Course course = optionalCourse.orElse(new Course());
        courseDao.delete(course);
    }

    public List<Course> getAllCourse() {
        List<Course> courseList = courseDao.findAll();
        return courseList;
    }


    public Course findCourse(int id) {
        Optional<Course> optionalCourse = courseDao.findById(id);
        Course course = optionalCourse.orElse(new Course());
        return course;
    }

    public Optional<Course> findByCourseId(Integer id) {
        return courseDao.findById(id);
    }


    public Course enrollStudentInCourse(Integer courseId, String email) {

        try {
            Course course = courseDao.findById(courseId)
                    .orElseThrow(() -> new IllegalArgumentException("Something went wrong"));
            User user = userDao.findByEmail(email)
                    .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));
            Role studentRole = roleDao.findByRoleName("STUDENT")
                    .orElseThrow(() -> new IllegalArgumentException("Role 'STUDENT' not found"));

            Student student;
            if (user.getRoles().stream().noneMatch(role ->
                             "STUDENT".equals(role.getRoleName()) ||
                            "ADMIN".equals(role.getRoleName()) ||
                            "INSTRUCTOR".equals(role.getRoleName()))) {
                student = new Student();
                student.setFirstName(user.getFirstName());
                student.setLastName(user.getLastName());
                student.setMobileNumber(user.getMobileNumber());
                student.setEmail(user.getEmail());
                studentDao.save(student);
                user.getRoles().add(studentRole);
                userDao.save(user);
            } else if (user.getRoles().stream().anyMatch(role -> "STUDENT".equals(role.getRoleName()))) {
                student = studentDao.findByEmail(email)
                        .orElseThrow(() -> new UsernameNotFoundException("Student not found with email: " + email));
            } else {
                throw new IllegalStateException("User does not have the required role.");
            }

            String courseType = course.getCourseType().getCourseTypeName();
            if ("offline".equals(courseType)) {
                course.getOfflineCourse().getBatchList().forEach(batch -> batch.getStudentList().add(student));
            } else if ("online".equals(courseType)) {
                course.getOnlineCourse().getStudentList().add(student);
            } else {
                throw new IllegalArgumentException("Invalid course type.");
            }

            return courseDao.save(course);
        } catch (Exception e) {
            throw new RuntimeException("Failed to enroll student in course", e);
        }
    }

}



