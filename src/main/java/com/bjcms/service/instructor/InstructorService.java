package com.bjcms.service.instructor;


import com.bjcms.entity.course.Course;
import com.bjcms.entity.instructor.Instructor;
import com.bjcms.responses.InstructorCreateRequest;

import java.util.List;

public interface InstructorService {
    public void addInstructor(InstructorCreateRequest instructorCreateRequest);
    public List<Instructor> addInstructors(List<Instructor> instructorList);

    public void deleteInstructor(Integer id);

    public void deleteAllInstructor();

    public Instructor updateInstructor(Instructor instructor);

    public Instructor findInstructor(Integer id);

    public List<Instructor> getAllInstructor();

    public List<Course> getAllInstructorCoursesById(Integer id);
}
