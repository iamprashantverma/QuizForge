package com.prashant.quizforge.server.service;

import com.prashant.quizforge.server.exception.EmailSendingException;

/**
 * Service interface for sending emails.
 * Implementations can use SMTP, JavaMail, or third-party providers.
 */
public interface EmailService {

    /**
     * Sends an email to a specified recipient.
     *
     * @param to      the recipient's email address
     * @param subject the subject of the email
     * @param body    the body/content of the email
     * @throws EmailSendingException if sending fails
     */
    void sendEmail(String to, String subject, String body);
}
