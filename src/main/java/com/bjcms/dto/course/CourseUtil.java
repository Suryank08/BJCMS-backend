package com.bjcms.dto.course;

import com.bjcms.dto.instructor.InstructorDto;
import com.bjcms.entity.course.Course;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class CourseUtil {
    public static List<CourseDetailedDto> parseCourseData(List<Course> courseList) {

        List<CourseDetailedDto> userCoursesDtoList = new ArrayList<>();
        if (courseList != null && !courseList.isEmpty()) {
            for (Course course : courseList) {
                OfflineCourseDto offlineCourseDto = null;
                OnlineCourseDto onlineCourseDto = null;
                List<InstructorDto> instructorDtoList = course.getInstructorList().stream()
                        .map(item -> new InstructorDto(item.getInstructorId(), item.getInstructorName(),item.getEmail(),item.getInstructorInfo().getInstructorInfoDetails()))
                        .collect(Collectors.toList());
                if (course.getCourseType().getCourseTypeName().equals("offline")) {
                    List<BatchDto> batchDtos = course.getOfflineCourse().getBatchList().stream().map(batch -> new BatchDto(batch.getBatchId(), batch.getBatchName(), batch.getBatchTime())).toList();
                    List<SubjectDto> subjectDtos = course.getOfflineCourse().getSubjectList().stream().map(subject -> new SubjectDto(subject.getSubjectId(), subject.getSubjectName())).toList();
                    offlineCourseDto = new OfflineCourseDto(course.getOfflineCourse().getCourseId(), course.getOfflineCourse().getStatus(), subjectDtos, batchDtos);
                } else if (course.getCourseType().getCourseTypeName().equals("online")) {
                    List<SubjectDto> subjectDtos = course.getOnlineCourse().getSubjectList().stream().map(subject -> new SubjectDto(subject.getSubjectId(), subject.getSubjectName())).toList();
                    onlineCourseDto = new OnlineCourseDto(course.getOnlineCourse().getOnlineCourseId(), course.getOnlineCourse().getStatus(), subjectDtos);
                }
                CourseDetailedDto userCoursesDto = new CourseDetailedDto(course.getCourseId(), course.getCourseImage(), course.getCourseName(), course.getCourseDuration(), course.getCourseCost(), course.getCourseDescription(), course.getStartDate(), course.getEndDate(), course.getCourseType().getCourseTypeName(), instructorDtoList, offlineCourseDto, onlineCourseDto);
                userCoursesDtoList.add(userCoursesDto);
            }
        }
        return userCoursesDtoList;
    }

        public static CourseDetailedDto parseCourseData(Course course) {

            // Initialize necessary variables for the DTOs
            OfflineCourseDto offlineCourseDto = null;
            OnlineCourseDto onlineCourseDto = null;

            // Create a list of InstructorDto objects from the course's instructor list
            List<InstructorDto> instructorDtoList = course.getInstructorList().stream()
                    .map(instructor -> new InstructorDto(
                            instructor.getInstructorId(),
                            instructor.getInstructorName(),
                            instructor.getEmail(),
                            instructor.getInstructorInfo().getInstructorInfoDetails()))
                    .collect(Collectors.toList());

            // Check the course type and populate the appropriate DTO
            if (course.getCourseType().getCourseTypeName().equalsIgnoreCase("offline")) {
                List<BatchDto> batchDtos = course.getOfflineCourse().getBatchList().stream()
                        .map(batch -> new BatchDto(batch.getBatchId(), batch.getBatchName(), batch.getBatchTime()))
                        .toList();

                List<SubjectDto> subjectDtos = course.getOfflineCourse().getSubjectList().stream()
                        .map(subject -> new SubjectDto(subject.getSubjectId(), subject.getSubjectName()))
                        .toList();

                offlineCourseDto = new OfflineCourseDto(
                        course.getOfflineCourse().getCourseId(),
                        course.getOfflineCourse().getStatus(),
                        subjectDtos,
                        batchDtos);
            } else if (course.getCourseType().getCourseTypeName().equalsIgnoreCase("online")) {
                List<SubjectDto> subjectDtos = course.getOnlineCourse().getSubjectList().stream()
                        .map(subject -> new SubjectDto(subject.getSubjectId(), subject.getSubjectName()))
                        .toList();

                onlineCourseDto = new OnlineCourseDto(
                        course.getOnlineCourse().getOnlineCourseId(),
                        course.getOnlineCourse().getStatus(),
                        subjectDtos);
            }

            // Create and return a CourseDetailedDto object with all the details
            return new CourseDetailedDto(
                    course.getCourseId(),
                    course.getCourseImage(),
                    course.getCourseName(),
                    course.getCourseDuration(),
                    course.getCourseCost(),
                    course.getCourseDescription(),
                    course.getStartDate(),
                    course.getEndDate(),
                    course.getCourseType().getCourseTypeName(),
                    instructorDtoList,
                    offlineCourseDto,
                    onlineCourseDto);
        }
         public static List<CourseSummaryDto> parseCourseSummaryDto(List<Course> courseList){
             List<CourseSummaryDto> courseSummaryDtoList = courseList.stream().map(course -> {
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
                 if(courseTypeName.equals("offline")){
                     courseStatus=course.getOfflineCourse().getStatus();
                 }else if(courseTypeName.equals("online")){
                     courseStatus=course.getOnlineCourse().getStatus();
                 }
                 return new CourseSummaryDto(courseId,courseImage,courseName,courseDuration,courseCost,startDate,endDate,courseTypeName,courseStatus);
             }).toList();
             return courseSummaryDtoList;
         }
    }