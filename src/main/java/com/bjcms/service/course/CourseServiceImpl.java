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
import com.bjcms.dto.coaching.CoachingDto;
import com.bjcms.dto.course.CourseDto;
import com.bjcms.dto.course.SubjectDto;
import com.bjcms.entity.coaching.Coaching;
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
import com.bjcms.responses.CourseCreationRequest;
import com.bjcms.service.coaching.CoachingService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;

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
    private CoachingService coachingService;

    @Autowired
    public CourseServiceImpl(CourseDao courseDao,CoachingService coachingService, BatchDao batchDao, SubjectDao subjectDao, InstructorDao instructorDao, VideoDao videoDao, CourseTypeDao courseTypeDao, UserDao userDao, StudentDao studentDao, RoleDao roleDao, OnlineCourseDao onlineCourseDao, OfflineCourseDao offlineCourseDao) {
        this.courseDao = courseDao;
        this.courseTypeDao = courseTypeDao;
        this.userDao = userDao;
        this.roleDao = roleDao;
        this.offlineCourseDao = offlineCourseDao;
        this.onlineCourseDao = onlineCourseDao;
        this.subjectDao = subjectDao;
        this.studentDao = studentDao;
        this.videoDao = videoDao;
        this.instructorDao = instructorDao;
        this.batchDao = batchDao;
        this.coachingService=coachingService;
    }

    @Transactional
    public Course addCourse(CourseCreationRequest courseCreationRequest, String email) {
        System.out.println("Starting addCourse method" + courseCreationRequest);
        Course course =new Course();
        // Handling CourseType
        course.setCourseImage(courseCreationRequest.getCourseImage());
        course.setCourseName(courseCreationRequest.getCourseName());
        course.setCourseDuration(courseCreationRequest.getCourseDuration());
        course.setCourseCost(courseCreationRequest.getCourseCost());
        course.setCourseDescription(courseCreationRequest.getCourseDescription());
        course.setStartDate(courseCreationRequest.getStartDate());
        course.setEndDate(courseCreationRequest.getEndDate());
        Coaching coaching=coachingService.findCoachingByCoachingId(Integer.parseInt(courseCreationRequest.getCoachingId()));
        if(coaching!=null){
            course.setCoaching(coaching);
        }else {
            throw new IllegalArgumentException("Coaching Id Not Found");
        }
        CourseType courseType = courseCreationRequest.getCourseType();
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
        OfflineCourse offlineCourse = courseCreationRequest.getOfflineCourse();
        OnlineCourse onlineCourse = courseCreationRequest.getOnlineCourse();
        if (courseType.getCourseTypeName().equals("offline") && offlineCourse != null && offlineCourse.getStatus() != null && !offlineCourse.getStatus().isEmpty()) {
            System.out.println("OfflineCourse present");

            offlineCourse.setCourse(savedCourse);
            OfflineCourse savedOfflineCourse = offlineCourseDao.save(offlineCourse);
            if (offlineCourse.getSubjectList() != null && !offlineCourse.getSubjectList().isEmpty()) {
                System.out.println("inside Subject");
                List<Subject> subjectList = savedOfflineCourse.getSubjectList();
                List<Subject> savedSubjects = subjectDao.saveAll(subjectList);
                savedOfflineCourse.setSubjectList(savedSubjects);
            }
            if (offlineCourse.getBatchList() != null && !offlineCourse.getBatchList().isEmpty()) {
                System.out.println("inside Batch");
                List<Batch> batchList = savedOfflineCourse.getBatchList();
                batchList.forEach(batch -> batch.setOfflineCourse(savedOfflineCourse));
                List<Batch> savedBatch = batchDao.saveAll(batchList);
                savedOfflineCourse.setBatchList(savedBatch);
            }
            // Link the OfflineCourse to the saved Course
            OfflineCourse finalSavedOfflineCourse = offlineCourseDao.save(savedOfflineCourse);
            savedCourse.setOfflineCourse(finalSavedOfflineCourse);
        } else if (courseType.getCourseTypeName().equals("online") && onlineCourse != null && onlineCourse.getStatus() != null && !onlineCourse.getStatus().isEmpty()) {
            onlineCourse.setCourse(savedCourse);
            System.out.println("OnlineCourse courseId: " + onlineCourse.getOnlineCourseId());

            if (onlineCourse.getSubjectList() != null && !onlineCourse.getSubjectList().isEmpty()) {
                System.out.println("inside Subject");
                List<Subject> subjectList = onlineCourse.getSubjectList();
                List<Subject> savedSubjects = subjectDao.saveAll(subjectList);
                onlineCourse.setSubjectList(savedSubjects);
            }
            if (onlineCourse.getStudentList() != null && !onlineCourse.getStudentList().isEmpty()) {
                System.out.println("inside Student");
                List<Student> studentList = onlineCourse.getStudentList();
                List<Student> savedStudentList = studentDao.saveAll(studentList);
                onlineCourse.setStudentList(savedStudentList);
            }
            if (onlineCourse.getVideoList() != null && !onlineCourse.getVideoList().isEmpty()) {
                System.out.println("inside Video");
                List<Video> videoList = onlineCourse.getVideoList();
                List<Video> savedVideoList = videoDao.saveAll(videoList);
                onlineCourse.setVideoList(savedVideoList);
            }
            onlineCourseDao.save(onlineCourse);
            savedCourse.setOnlineCourse(onlineCourse);

        }
        //TODO later upgrade instructor functionality to add multiple instructors for time being i am setting Course  instructorList to instructor who has currently logged in
        List<Instructor> instructorList = courseCreationRequest.getInstructorList();
        if (instructorList != null && !instructorList.isEmpty()) {
            System.out.println("inside Instructor");
//        for (Instructor instructor : instructorList) {
//            if (!instructor.getCourseList().contains(savedCourse)) {
//                instructor.getCourseList().add(savedCourse);
//            }
//        }
            Optional<Instructor> optionalInstructor = instructorDao.findByEmail(email);
            List<Instructor> instructors =new ArrayList<>();
            if(optionalInstructor.isPresent()) {
                instructors.add(optionalInstructor.get());
            }
            else{
                instructors= instructorDao.saveAll(instructorList);
            }
            savedCourse.setInstructorList(instructors);
        }


        Course finalSavedCourse = courseDao.save(savedCourse);
        return finalSavedCourse;
    }

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

    public List<Course> getAllCourseByCoachingId(Integer coachingId) {
        Coaching coaching= coachingService.findCoachingByCoachingId(coachingId);
        List<Course> courseList = coaching.getCoursesList();
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


    public Course enrollStudentInCourse(Integer courseId, String email, Integer batchId) {

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
                            "CO-ADMIN".equals(role.getRoleName())||
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
                Batch batch = batchDao.findById(batchId)
                        .orElseThrow(() -> new IllegalArgumentException("Something went wrong"));
                if (!student.getBatchList().contains(batch)) {
                    student.getBatchList().add(batch);
                    studentDao.save(student);
                } else {
                    // Handle the case where the student is already enrolled
                    System.out.println("Student is already enrolled in this batch.");
                }
//                batch.getStudentList().add(student);
//                Batch savedBatch= batchDao.save(batch);
//                student.getBatchList().add(batch);
//                studentDao.save(student);
//           course.getOfflineCourse().getBatchList().forEach(batch -> batch.getStudentList().add(student));
            } else if ("online".equals(courseType)) {
                if (!student.getOnlineCourseList().contains(course.getOnlineCourse())) {
                    student.getOnlineCourseList().add(course.getOnlineCourse());
                    studentDao.save(student);
                } else {
                    // Handle the case where the student is already enrolled
                    System.out.println("Student is already enrolled in this Online Course.");
                }
//                course.getOnlineCourse().getStudentList().add(student);
//                student.getOnlineCourseList().add(course.getOnlineCourse());
            } else {
                throw new IllegalArgumentException("Invalid course type.");
            }

            return courseDao.save(course);
        } catch (Exception e) {
            throw new RuntimeException("Failed to enroll student in course", e);
        }
    }

    public  List<Course> enrolledCourses(String userName){
        try {
            Student student = studentDao.findByEmail(userName).orElseThrow(() -> new IllegalArgumentException("You are not enrolled in any courses"));
            List<OnlineCourse> enrolledOnlineCourseList = student.getOnlineCourseList();
            List<Batch> enrolledBatchList = student.getBatchList();
            List<Course> enrolledCousreList = new ArrayList<>();
            List<OfflineCourse> enrolledOfflineCourseList=new ArrayList<>();
            for(Batch batch:enrolledBatchList){
                if(!enrolledOfflineCourseList.contains(batch.getOfflineCourse())) {
                    enrolledOfflineCourseList.add(batch.getOfflineCourse());
                }
            }
            for(OnlineCourse onlineCourse :enrolledOnlineCourseList){
                enrolledCousreList.add(onlineCourse.getCourse());
            }
            for(OfflineCourse offlineCourse :enrolledOfflineCourseList){
                enrolledCousreList.add(offlineCourse.getCourse());
            }
            return enrolledCousreList;
        }catch (Exception e){
            throw new RuntimeException("Failed to enroll student in course", e);
        }

    }

    public List<CourseDto>instructorCourses(String userName) {
        try {
            Instructor instructor = instructorDao.findByEmail(userName).orElseThrow(() -> new IllegalArgumentException("Can not find Instructor"));
            List<Course> courseList = instructor.getCourseList();
            List<CourseDto> courseDtoList = courseList.stream().map(course -> {
                Integer courseId=course.getCourseId();
                String courseName=course.getCourseName();
                String courseImage=course.getCourseImage();
                String courseDuration=course.getCourseDuration();
                String courseCost=course.getCourseCost();
                String courseDescription=course.getCourseDescription();
                String courseTypeName=course.getCourseType().getCourseTypeName();
                Date startDate=course.getStartDate();
                Date endDate =course.getEndDate();
                String courseStatus=null;
                List<SubjectDto> subjectDtoList=new ArrayList<>();
              if(courseTypeName.equals("offline")){
                  courseStatus=course.getOfflineCourse().getStatus();
                  subjectDtoList.addAll(course.getOfflineCourse().getSubjectList().stream().map(subject -> new SubjectDto(subject.getSubjectId(),subject.getSubjectName())).toList());
              }else if(courseTypeName.equals("online")){
                  subjectDtoList.addAll(course.getOnlineCourse().getSubjectList().stream().map(subject -> new SubjectDto(subject.getSubjectId(),subject.getSubjectName())).toList());
              }
            return new CourseDto(courseId,courseImage,courseName,courseDuration,courseCost,courseDescription,startDate,endDate,courseTypeName,courseStatus,subjectDtoList);
            }).toList();

            return courseDtoList;
        } catch (Exception e) {
            throw new RuntimeException("Failed to get Instructor course", e);
        }
    }

}



