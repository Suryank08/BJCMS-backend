package com.bjcms.service.course;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bjcms.dao.course.SubjectDao;
import com.bjcms.entity.course.Subject;
import com.bjcms.entity.course.offline.OfflineCourse;
import com.bjcms.entity.course.online.OnlineCourse;
import com.bjcms.entity.course.online.Video;
import com.bjcms.entity.instructor.Instructor;
import com.bjcms.service.course.offline.OfflineCourseService;
import com.bjcms.service.course.online.OnlineCourseService;
import com.bjcms.service.course.online.VideoService;

import jakarta.transaction.Transactional;

@Service
public class SubjectServiceImpl implements SubjectService {
    private  SubjectDao subjectDao;
    private  OnlineCourseService onlineCourseService;
    private  OfflineCourseService offlineCourseService;
    private  VideoService videoService;

    @Autowired
    public SubjectServiceImpl(SubjectDao subjectDao, OnlineCourseService onlineCourseService, OfflineCourseService offlineCourseService, VideoService videoService) {
        this.subjectDao = subjectDao;
        this.onlineCourseService = onlineCourseService;
        this.offlineCourseService = offlineCourseService;
        this.videoService = videoService;
    }

    @Transactional
    public Subject addSubject(Subject subject) {
        return subjectDao.save(subject);
    }

    @Transactional
    public List<Subject> addSubjects(List<Subject> subjectList) {
        for (Subject subject : subjectList) {
            List<Instructor> instructorList = subject.getInstructorList();
            List<OnlineCourse> onlineCourseList = subject.getOnlineCourseList();
            List<OfflineCourse> offlineCourseList = subject.getOfflineCourseList();
            List<Video> videoList = subject.getVideoList();

            if(onlineCourseList!=null){
                onlineCourseService.addOnlineCourses(onlineCourseList);
                subject.setOnlineCourseList(onlineCourseList);
            }
            if(offlineCourseList!=null){
                offlineCourseService.addOfflineCourses(offlineCourseList);
                subject.setOfflineCourseList(offlineCourseList);
            }
            if(videoList!=null){
                videoService.addVideos(videoList);
                subject.setVideoList(videoList);
            }
            if (instructorList != null) {
                subject.setInstructorList(instructorList);
            }
            subjectDao.save(subject);
        }
        return subjectDao.findAll();
    }

    @Transactional
    public Subject updateSubject(Subject updateSubject) {
        Subject existingSubject =subjectDao.findById(updateSubject.getSubjectId())
                .orElseThrow(()-> new RuntimeException("Subject Id  Not Found "+updateSubject.getSubjectId()));
        if(updateSubject.getSubjectName()!=null){
            existingSubject.setSubjectName(updateSubject.getSubjectName());
        }
        return subjectDao.save(existingSubject);
    }


    public List<Subject> getAllSubject() {
        List<Subject> subjectList = subjectDao.findAll();
        return subjectList;
    }


    public Subject findSubject(Integer id) {
        Optional<Subject> optionalSubject = subjectDao.findById(id);
        Subject subject = optionalSubject.orElse(new Subject());
        return subject;
    }

    public Optional<Subject> findBySubjectId(Integer id) {
        return subjectDao.findById(id);
    }

    @Transactional
    public void deleteSubject(Integer id) {
        Optional<Subject> optionalSubject = subjectDao.findById(id);
        Subject subject = optionalSubject.orElse(new Subject());
        subjectDao.delete(subject);
    }

    @Transactional
    public void deleteAllSubject() {
        List<Subject> subjects = subjectDao.findAll();
        subjects.forEach(subject -> subjectDao.delete(subject));
    }

}
