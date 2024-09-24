package com.bjcms.dto.course;

import com.bjcms.dto.instructor.InstructorDto;
import com.bjcms.entity.course.Course;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CourseUtil {
    public static List<UserCoursesDto> parseCourseData(List<Course> courseList) {

        List<UserCoursesDto> userCoursesDtoList = new ArrayList<>();
        if (courseList != null && !courseList.isEmpty()) {
            for (Course course : courseList) {
                OfflineCourseDto offlineCourseDto = null;
                OnlineCourseDto onlineCourseDto = null;
                List<InstructorDto> instructorDtoList = course.getInstructorList().stream()
                        .map(item -> new InstructorDto(item.getInstructorId(), item.getInstructorName()))
                        .collect(Collectors.toList());
                if (course.getCourseType().getCourseTypeName().equals("offline")) {
                    List<BatchDto> batchDtos = course.getOfflineCourse().getBatchList().stream().map(batch -> new BatchDto(batch.getBatchId(), batch.getBatchName(), batch.getBatchTime())).toList();
                    List<SubjectDto> subjectDtos = course.getOfflineCourse().getSubjectList().stream().map(subject -> new SubjectDto(subject.getSubjectId(), subject.getSubjectName())).toList();
                    offlineCourseDto = new OfflineCourseDto(course.getOfflineCourse().getCourseId(), course.getOfflineCourse().getStatus(), subjectDtos, batchDtos);
                } else if (course.getCourseType().getCourseTypeName().equals("online")) {
                    List<SubjectDto> subjectDtos = course.getOnlineCourse().getSubjectList().stream().map(subject -> new SubjectDto(subject.getSubjectId(), subject.getSubjectName())).toList();
                    onlineCourseDto = new OnlineCourseDto(course.getOnlineCourse().getOnlineCourseId(), course.getOnlineCourse().getStatus(), subjectDtos);
                }
                UserCoursesDto userCoursesDto = new UserCoursesDto(course.getCourseId(), course.getCourseImage(), course.getCourseName(), course.getCourseDuration(), course.getCourseCost(), course.getCourseDescription(), course.getStartDate(), course.getEndDate(), course.getCourseType().getCourseTypeName(), instructorDtoList, offlineCourseDto, onlineCourseDto);
                userCoursesDtoList.add(userCoursesDto);
            }
        }
        return userCoursesDtoList;
    }

}
