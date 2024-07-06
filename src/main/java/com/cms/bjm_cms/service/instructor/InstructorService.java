package com.cms.bjm_cms.service.instructor;


import com.cms.bjm_cms.entity.course.Course;
import com.cms.bjm_cms.entity.instructor.Instructor;

import java.util.List;

public interface InstructorService {
    public Instructor addInstructor(Instructor instructor);
    public List<Instructor> addInstructors(List<Instructor> instructorList);

    public void deleteInstructor(Integer id);

    public void deleteAllInstructor();

    public Instructor updateInstructor(Instructor instructor);

    public Instructor findInstructor(Integer id);

    public List<Instructor> getAllInstructor();

    public List<Course> getAllInstructorCoursesById(Integer id);
}
