package com.bjcms.service.instructor;


import com.bjcms.config.authentication.RandomPasswordGenerator;
import com.bjcms.dao.instructor.InstructorDao;
import com.bjcms.dao.user.RoleDao;
import com.bjcms.dao.user.UserDao;
import com.bjcms.dto.instructor.InstructorDto;
import com.bjcms.entity.coaching.Coaching;
import com.bjcms.entity.course.Course;
import com.bjcms.entity.course.Subject;
import com.bjcms.entity.instructor.Instructor;
import com.bjcms.entity.instructor.InstructorInfo;
import com.bjcms.entity.instructor.Qualification;
import com.bjcms.entity.user.Role;
import com.bjcms.entity.user.User;
import com.bjcms.responses.InstructorCreateRequest;
import com.bjcms.service.Email.EmailSenderService;
import com.bjcms.service.coaching.CoachingService;
import com.bjcms.service.course.CourseService;
import com.bjcms.service.course.SubjectService;
import com.bjcms.service.user.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class InstructorServiceImpl implements InstructorService {


    private InstructorDao instructorDao;
    private CourseService courseService;
    private InstructorInfoService instructorInfoService;
    private SubjectService subjectService;
    private QualificationService qualificationService;
    private CoachingService coachingService;
    private UserDao userDao;
    private RoleDao roleDao;
    private UserService userService;
    private EmailSenderService emailSenderService;

    @Autowired
    public InstructorServiceImpl(InstructorDao instructorDao,EmailSenderService emailSenderService,UserService userService, CourseService courseService,RoleDao roleDao, UserDao userDao,InstructorInfoService instructorInfoService, SubjectService subjectService, QualificationService qualificationService, CoachingService coachingService) {
        this.instructorDao = instructorDao;
        this.courseService = courseService;
        this.instructorInfoService = instructorInfoService;
        this.subjectService = subjectService;
        this.qualificationService = qualificationService;
        this.coachingService = coachingService;
        this.userDao=userDao;
        this.roleDao=roleDao;
        this.userService=userService;
        this.emailSenderService=emailSenderService;
    }

    @Transactional
    public  Instructor addInstructor(InstructorCreateRequest instructorCreateRequest) {
        Coaching coaching = coachingService.findCoachingByCoachingId(instructorCreateRequest.getCoachingId());
        String email = instructorCreateRequest.getEmail();

        if (coaching.getInstructorList().stream()
                .anyMatch(instructor -> instructor.getEmail().equals(email))) {
            throw new IllegalArgumentException("Instructor is already register with Your Coaching");
        }
        else {
           Optional<Instructor> instructorOptional=instructorDao.findByEmail(email);
            List<Coaching> coachingList= new ArrayList<>();
            coachingList.add(coaching);
           if(instructorOptional.isPresent()){
               Instructor instructor =instructorOptional.get();
               instructor.setCoachingList(coachingList);
               return instructorDao.save(instructor);
           }else{
               Optional<User> userOptional=userDao.findByEmail(email);
               if(userOptional.isPresent()){
//                   User user =userOptional.get();
//                   Role instructorRole = roleDao.findByRoleName("INSTRUCTOR")
//                           .orElseThrow(() -> new IllegalArgumentException("Role 'INSTRUCTOR' not found"));
//                   Instructor instructor;
//                   if (user.getRoles().stream().noneMatch(role ->
//                           "STUDENT".equals(role.getRoleName()) ||
//                                   "ADMIN".equals(role.getRoleName()) ||
//                                   "CO-ADMIN".equals(role.getRoleName())||
//                                   "INSTRUCTOR".equals(role.getRoleName()))) {
//                       List<Qualification> qualificationList= qualificationService.findAllQualificationBysIds(instructorCreateRequest.getQualificationList());
//                       List<Subject> subjectList=subjectService.findAllSubjectByIds(instructorCreateRequest.getSubjectList());
//                      instructor = new Instructor();
//                       instructor.setInstructorName(instructorCreateRequest.getInstructorName());
//                       instructor.setQualificationList(qualificationList);
//                       instructor.setSubjectList(subjectList);
//                       instructor.setCoachingList(coachingList);
//                       instructor.setEmail(email);
//
//                       // Create and save InstructorInfo
//                       InstructorInfo instructorInfo = instructorCreateRequest.getInstructorInfo();
//
//                       // Set bidirectional relationship
//                       instructorInfo.setInstructor(instructor);  // Set Instructor in InstructorInfo
//                       InstructorInfo savedInstructorInfo = instructorInfoService.addInstructorInfo(instructorInfo);
//
//                       // Now set the saved InstructorInfo in Instructor
//                       instructor.setInstructorInfo(savedInstructorInfo);
//
//                       // Save the Instructor entity (with cascading, this will save InstructorInfo as well)
//                       Instructor savedInstructor = instructorDao.save(instructor);
//
//                       // Assign role to user and save user
//                       user.getRoles().add(instructorRole);
//                       userDao.save(user);
//
//                       return savedInstructor;  // Return the saved instructor
//                   }else{
//                       throw  new IllegalArgumentException("User has already have an another role!!");
//                   }
                   User user =userOptional.get();
                   return userToInstructor(user,instructorCreateRequest,coachingList);

               }else {
                   String[] nameArr =instructorCreateRequest.getInstructorName().split("\\s+");
                   String fName=nameArr[0];
                   String lName= "";
                   if(!nameArr[1].isEmpty()) {
                       lName = nameArr[1];
                   }
                   String mobileNum=instructorCreateRequest.getMobileNumber();
                   String randomPassword= RandomPasswordGenerator.generatePassword(10,fName,mobileNum,email);
                   System.out.println(randomPassword);
                   User user=new User(fName,lName,email,mobileNum,randomPassword);
                   User savedUser=userService.addUser(user);
                   if(savedUser!=null) {
                      Instructor savedInstructor= userToInstructor(savedUser, instructorCreateRequest, coachingList);
                        if(savedInstructor!=null){
                            emailSenderService.sendWelcomeEmail(fName, lName, email, randomPassword, coaching);
                            return savedInstructor;
                        }else {
                            throw new IllegalArgumentException("Instructor cannot be created!");
                        }
                   }else{
                       throw  new IllegalArgumentException("User Cannot be Created!!");
                   }
//                   throw new IllegalArgumentException("User is not Registered with Our Platform");
               }
           }
        }
    }

@Transactional
public Instructor userToInstructor(User user ,InstructorCreateRequest instructorCreateRequest,List<Coaching> coachingList){
//    User user =userOptional.get();
    String email =instructorCreateRequest.getEmail();
    Role instructorRole = roleDao.findByRoleName("INSTRUCTOR")
            .orElseThrow(() -> new IllegalArgumentException("Role 'INSTRUCTOR' not found"));
    Instructor instructor;
    if (user.getRoles().stream().noneMatch(role ->
            "STUDENT".equals(role.getRoleName()) ||
                    "ADMIN".equals(role.getRoleName()) ||
                    "CO-ADMIN".equals(role.getRoleName())||
                    "INSTRUCTOR".equals(role.getRoleName()))) {
        List<Qualification> qualificationList= qualificationService.findAllQualificationBysIds(instructorCreateRequest.getQualificationList());
        List<Subject> subjectList=subjectService.findAllSubjectByIds(instructorCreateRequest.getSubjectList());
        instructor = new Instructor();
        instructor.setInstructorName(instructorCreateRequest.getInstructorName());
        instructor.setQualificationList(qualificationList);
        instructor.setSubjectList(subjectList);
        instructor.setCoachingList(coachingList);
        instructor.setEmail(email);

        // Create and save InstructorInfo
        InstructorInfo instructorInfo = instructorCreateRequest.getInstructorInfo();

        // Set bidirectional relationship
        instructorInfo.setInstructor(instructor);  // Set Instructor in InstructorInfo
        InstructorInfo savedInstructorInfo = instructorInfoService.addInstructorInfo(instructorInfo);

        // Now set the saved InstructorInfo in Instructor
        instructor.setInstructorInfo(savedInstructorInfo);

        // Save the Instructor entity (with cascading, this will save InstructorInfo as well)
        Instructor savedInstructor = instructorDao.save(instructor);

        // Assign role to user and save user
        user.getRoles().add(instructorRole);
        userDao.save(user);

        return savedInstructor;  // Return the saved instructor
    }else{
        throw  new IllegalArgumentException("User has already have an another role!!");
    }
}
    @Transactional
    public List<Instructor> addInstructors(List<Instructor> instructorList) {
        for (Instructor instructor : instructorList) {
            InstructorInfo instructorInfo = instructor.getInstructorInfo();
            List<Course> courseList = instructor.getCourseList();
            List<Subject> subjectList = instructor.getSubjectList();
            List<Qualification> qualificationList = instructor.getQualificationList();

            if (instructorInfo != null) {
                instructorInfoService.addInstructorInfo(instructorInfo);
                instructor.setInstructorInfo(instructorInfo);

            }
            if (courseList != null) {
                courseService.addCourses(courseList);
                instructor.setCourseList(courseList);
            }
            if (subjectList != null) {
                subjectService.addSubjects(subjectList);
                instructor.setSubjectList(subjectList);
            }
            if (qualificationList != null) {
                qualificationService.addQualifications(qualificationList);
                instructor.setQualificationList(qualificationList);
            }
            instructorDao.save(instructor);
        }

        return instructorDao.findAll();
    }

    @Transactional
    public Instructor updateInstructor(Instructor updatedInstructor) {
        Instructor existingInstructor = instructorDao.findById(updatedInstructor.getInstructorId())
                .orElseThrow(() -> new RuntimeException("Instructor not found with id: " + updatedInstructor.getInstructorId()));

        if (updatedInstructor.getInstructorName() != null) {
            //instructorName Cannot be null
            existingInstructor.setInstructorName(updatedInstructor.getInstructorName());
        }

        if (updatedInstructor.getInstructorInfo() != null) {
            InstructorInfo updatedInfo = updatedInstructor.getInstructorInfo();

            if (existingInstructor.getInstructorInfo() == null) {
                // If no existing InstructorInfo, set the new one
                existingInstructor.setInstructorInfo(updatedInfo);
                updatedInfo.setInstructor(existingInstructor);
            } else {
                // Update the existing InstructorInfo details
                InstructorInfo existingInfo = existingInstructor.getInstructorInfo();
                existingInfo.setInstructorInfoDetails(updatedInfo.getInstructorInfoDetails());
                existingInfo.setInstructor(existingInstructor);

                // Ensure the identifier is set properly for the updatedInfo
                if (updatedInfo.getInstructorId() != null) {
                    existingInfo.setInstructorId(updatedInfo.getInstructorId());
                }
            }
        }
        if (updatedInstructor.getQualificationList() != null) {
            List<Instructor> tempInstructorList = new ArrayList<>();
            tempInstructorList.add(existingInstructor);

            if (existingInstructor.getQualificationList().isEmpty()) {
                existingInstructor.setQualificationList(updatedInstructor.getQualificationList());
            } else {
                List<Qualification> qualificationList = existingInstructor.getQualificationList();
                for (Qualification existingQualification : qualificationList) {
                    updatedInstructor.getQualificationList().stream()
                            .filter(updatedQualification -> updatedQualification.getQualificationId().equals(existingQualification.getQualificationId()))
                            .findFirst()
                            .ifPresent(updatedQualification -> {
                                existingQualification.setQualificationName(updatedQualification.getQualificationName());
                                existingQualification.setInstructorList(tempInstructorList);
                                if (updatedQualification.getQualificationId() != null) {
                                    existingQualification.setQualificationId(updatedQualification.getQualificationId());
                                }
                            });
                }
            }
        }
        // Save the updated instructor entity
        return instructorDao.save(existingInstructor);
    }


    @Transactional
    public void deleteInstructor(Integer id) {
        Optional<Instructor> optionalInstructor = instructorDao.findById(id);
        Instructor instructor = optionalInstructor.orElse(new Instructor());
        instructorDao.delete(instructor);
    }

    @Transactional
    public void deleteAllInstructor() {
        List<Instructor> instructors = instructorDao.findAll();
        instructors.forEach(instructor -> instructorDao.delete(instructor));
    }


    public Instructor findInstructor(Integer id) {
        Optional<Instructor> optionalInstructor = instructorDao.findById(id);
        Instructor instructor = optionalInstructor.orElse(new Instructor());
        return instructor;
    }

    public List<Instructor> getAllInstructor() {
        List<Instructor> instructorList = instructorDao.findAll();
        return instructorList;
    }

    public List<Course> getAllInstructorCoursesById(Integer id) {
        Optional<Instructor> optionalInstructor = instructorDao.findById(id);
        Instructor instructor = optionalInstructor.orElse(new Instructor());
        return instructor.getCourseList();
    }
    public List<InstructorDto> getInstructorsByCoachingId(Integer coachingId){
        Coaching coaching=coachingService.findCoachingByCoachingId(coachingId);
        List<Instructor> instructorList=coaching.getInstructorList();
      //  List<InstructorDto> instructorDtoList=instructorList.stream().map(instructor -> new InstructorDto(instructor.getInstructorId(),instructor.getInstructorName(),instructor.getInstructorInfo().getInstructorInfoDetails())).toList();
      List<InstructorDto>instructorDtoList= instructorList.stream().map(instructor -> new InstructorDto(instructor.getInstructorId(),instructor.getInstructorName(),instructor.getInstructorInfo().getInstructorInfoDetails(),instructor.getQualificationList().stream().map(Qualification::getQualificationName).toList(),instructor.getSubjectList().stream().map(Subject::getSubjectName).toList(),instructor.getCourseList().stream().map(Course::getCourseName).toList())).toList();
        return instructorDtoList;
    }

}
