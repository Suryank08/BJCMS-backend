package com.bjcms.service.Email;

public interface EmailSenderService {
    public void sendEmail(String toEmail,String subject,String body);
}
