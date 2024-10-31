package com.bjcms.service.Email;

import com.bjcms.dao.course.offline.BatchDao;
import com.bjcms.entity.coaching.Coaching;
import com.bjcms.entity.course.Course;
import com.bjcms.entity.course.offline.Batch;
import com.bjcms.entity.instructor.Instructor;
import com.bjcms.entity.student.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class EmailSenderServiceImpl implements EmailSenderService{
    private static final Logger log = LoggerFactory.getLogger(EmailSenderServiceImpl.class);
    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private BatchDao batchDao;

    public void sendEmail(String toEmail,String subject,String body){
        SimpleMailMessage message=new SimpleMailMessage();
        message.setTo(toEmail);
        message.setText(body);
        message.setSubject(subject);
        mailSender.send(message);
        log.info("Email Send Sucessfully"+toEmail);
    }
 public void sendWelcomeEmail(String fName, String lName, String email, String password, Coaching coaching) {
        String subject = "Welcome to BJCMS! We're Excited to Have You!";
        StringBuffer message = new StringBuffer();

        message.append("Dear ").append(fName).append(" ").append(lName).append(",\n\n")
                .append("Congratulations on becoming an instructor in our ")
                .append(coaching.getCoachingName()).append(" platform!\n")
                .append("Your account has been set up by the ")
                .append(coaching.getCoachingAdmin().getCoachingAdminName()).append(" (Coaching Admin).\n\n")
                .append("Here are your login details:\n")
                .append("User ID: ").append(email).append("\n")
                .append("Password: ").append(password).append("\n\n")
                .append("Link to Login: http://localhost:3000/")
                .append(coaching.getCoachingName().replace(" ", "-")).append("\n")
                .append("Please change your password after your first login.\n")
                .append("We’re excited to have you on board!\n\n")
                .append("If you have any questions, don’t hesitate to reach out.\n\n")
                .append("Best regards,\n")
                .append("The BJCMS Team");

     sendEmail(email, subject, message.toString());
    }
    public void absentStudentEmail(String email, Course course, Student student, Integer batchId) {
        Batch batch = null;
        if (batchId != null) {
            batch = batchDao.findById(batchId).orElseThrow(() -> new IllegalArgumentException("No such Batch ID found"));
        }

        String subject = "Absence Notification for " + course.getCourseName() + " Course";
        StringBuilder sb = new StringBuilder();
        sb.append(student.getFirstName()).append(" ").append(student.getLastName());

        if (batch != null) {
            sb.append(" was absent in the ");
            sb.append("Batch: ").append(batch.getBatchName()).append(" ");
            sb.append("scheduled at ").append(batch.getBatchTime()).append(" ");
            sb.append("for the ").append(course.getCourseName()).append(" course.");
        } else {
            sb.append(" was absent on ");
            sb.append(LocalDate.now(ZoneId.of("Asia/Kolkata")).toString());
            sb.append(" in the ").append(course.getCourseName()).append(" course.");
        }

        sb.append(System.lineSeparator()).append("Instructor(s): ");
        sb.append(course.getInstructorList().stream()
                .map(Instructor::getInstructorName)
                .filter(Objects::nonNull)
                .collect(Collectors.joining(", ")));

        String body = sb.toString();
        sendEmail(email, subject, body);
    }

}
