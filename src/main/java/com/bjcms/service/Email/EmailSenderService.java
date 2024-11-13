package com.bjcms.service.Email;

import com.bjcms.entity.coaching.Coaching;
import com.bjcms.entity.course.Course;
import com.bjcms.entity.student.Student;

public interface EmailSenderService {
    public void sendEmail(String toEmail,String subject,String body);
    public void sendWelcomeEmail(String fName, String lName, String  email, String  randomPassword, Coaching coaching);
    public void absentStudentEmail(String email, Course course, Student student,Integer batchId);
}
