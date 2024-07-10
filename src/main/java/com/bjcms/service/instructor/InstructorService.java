package com.bjcms.service.instructor;


import java.util.List;

import com.bjcms.entity.course.Course;
import com.bjcms.entity.instructor.Instructor;

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
