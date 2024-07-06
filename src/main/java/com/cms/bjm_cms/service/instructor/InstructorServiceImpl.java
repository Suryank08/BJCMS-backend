package com.cms.bjm_cms.service.instructor;


import com.cms.bjm_cms.dao.instructor.InstructorDao;
import com.cms.bjm_cms.entity.course.Course;
import com.cms.bjm_cms.entity.course.Subject;
import com.cms.bjm_cms.entity.instructor.Instructor;
import com.cms.bjm_cms.entity.instructor.InstructorInfo;
import com.cms.bjm_cms.entity.instructor.Qualification;
import com.cms.bjm_cms.service.course.CourseService;
import com.cms.bjm_cms.service.course.SubjectService;
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

    @Autowired
    public InstructorServiceImpl(InstructorDao instructorDao, CourseService courseService, InstructorInfoService instructorInfoService, SubjectService subjectService, QualificationService qualificationService) {
        this.instructorDao = instructorDao;
        this.courseService = courseService;
        this.instructorInfoService = instructorInfoService;
        this.subjectService = subjectService;
        this.qualificationService = qualificationService;
    }


    @Transactional
    public Instructor addInstructor(Instructor instructor) {
        return instructorDao.save(instructor);
    }

    @Transactional
    public List<Instructor> addInstructors(List<Instructor> instructorList) {
        System.out.println("Saving Instructor: " + instructorList.toString());
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

}
