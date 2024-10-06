package com.bjcms.service.instructor;


import com.bjcms.dto.instructor.InstructorDto;
import com.bjcms.entity.course.Course;
import com.bjcms.entity.instructor.Instructor;
import com.bjcms.responses.InstructorCreateRequest;

import java.util.List;
import java.util.Optional;

public interface InstructorService {
    public Instructor addInstructor(InstructorCreateRequest instructorCreateRequest);
    public List<Instructor> addInstructors(List<Instructor> instructorList);

    public void deleteInstructor(Integer id);

    public void deleteAllInstructor();

    public Instructor updateInstructor(Instructor instructor);

    public Instructor findInstructor(Integer id);

    public List<Instructor> getAllInstructor();

    public List<Course> getAllInstructorCoursesById(Integer id);

    public List<InstructorDto> getInstructorsByCoachingId(Integer coachingId);
}
