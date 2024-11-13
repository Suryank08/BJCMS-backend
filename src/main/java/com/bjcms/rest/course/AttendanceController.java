package com.bjcms.rest.course;

import com.bjcms.responses.AttendanceRequest;
import com.bjcms.service.course.CourseService;
import com.bjcms.service.course.offline.OfflineCourseAttendanceService;
import com.bjcms.service.course.online.OnlineCourseAttendenceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/attendances")
public class AttendanceController {

    private static final Logger logger = LoggerFactory.getLogger(AttendanceController.class);

    private final CourseService courseService;
    private final OfflineCourseAttendanceService offlineCourseAttendanceService;
    private final OnlineCourseAttendenceService onlineCourseAttendenceService;

    public AttendanceController(CourseService courseService,
                                OfflineCourseAttendanceService offlineCourseAttendanceService,
                                OnlineCourseAttendenceService onlineCourseAttendenceService) {
        this.courseService = courseService;
        this.offlineCourseAttendanceService = offlineCourseAttendanceService;
        this.onlineCourseAttendenceService = onlineCourseAttendenceService;
    }

    @PostMapping("/addAttendance")
    @PreAuthorize("hasAnyAuthority('ADMIN','INSTRUCTOR')")
    public ResponseEntity<String> addAttendance(@RequestBody AttendanceRequest attendanceRequest) {
        logger.info("Received attendance request: {}", attendanceRequest);

        try {
            return courseService.findByCourseId(attendanceRequest.getCourseId())
                    .map(course -> {
                        logger.info("Course found: {}", course.getCourseType().getCourseTypeName());

                        try {
                            if ("offline".equalsIgnoreCase(course.getCourseType().getCourseTypeName())) {
                                offlineCourseAttendanceService.addAttendance(attendanceRequest);
                                return new ResponseEntity<>("Offline attendance added successfully", HttpStatus.OK);
                            } else if ("online".equalsIgnoreCase(course.getCourseType().getCourseTypeName())) {
                                onlineCourseAttendenceService.addAttendence(attendanceRequest);
                                return new ResponseEntity<>("Online attendance added successfully", HttpStatus.OK);
                            } else {
                                logger.error("Invalid course type for courseId: {}", attendanceRequest.getCourseId());
                                return new ResponseEntity<>("Invalid course type!", HttpStatus.BAD_REQUEST);
                            }
                        } catch (Exception e) {
                            // Handling exceptions from the service layer
                            logger.error("Error in attendance service for courseId: {}", attendanceRequest.getCourseId(), e);
                            return new ResponseEntity<>("Error occurred while adding attendance: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
                        }
                    })
                    .orElseGet(() -> {
                        logger.error("Course not found for courseId: {}", attendanceRequest.getCourseId());
                        return new ResponseEntity<>("Course not found!", HttpStatus.NOT_FOUND);
                    });

        } catch (IllegalArgumentException e) {
            logger.error("Invalid input: {}", e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            logger.error("Unexpected error: {}", e.getMessage());
            return new ResponseEntity<>("An unexpected error occurred", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }




//    public void addAttendance(@RequestBody AttendanceRequest attendanceRequest) {
//        logger.info("Received attendance request: {}", attendanceRequest);
//
//        courseService.findByCourseId(attendanceRequest.getCourseId())
//                .ifPresentOrElse(course -> {
//                    logger.info("Course found: {}", course.getCourseType().getCourseTypeName());
//
//                    if ("offline".equalsIgnoreCase(course.getCourseType().getCourseTypeName())) {
//                        offlineCourseAttendanceService.addAttendance();
//                    } else if ("online".equalsIgnoreCase(course.getCourseType().getCourseTypeName())) {
//                        onlineCourseAttendenceService.addAttendence(attendanceRequest);
//                    } else {
//                        logger.error("Invalid course type for courseId: {}", attendanceRequest.getCourseId());
//                        throw new IllegalArgumentException("Invalid course type!");
//                    }
//                }, () -> {
//                    logger.error("Course not found for courseId: {}", attendanceRequest.getCourseId());
//                    throw new IllegalArgumentException("Course not found!");
//                });
//    }


}

