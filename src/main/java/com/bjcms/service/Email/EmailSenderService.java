package com.bjcms.service.Email;

import com.bjcms.entity.coaching.Coaching;

public interface EmailSenderService {
    public void sendEmail(String toEmail,String subject,String body);
    public void sendWelcomeEmail(String fName, String lName, String  email, String  randomPassword, Coaching coaching);
}
