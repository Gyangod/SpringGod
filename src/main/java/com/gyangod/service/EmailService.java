package com.gyangod.service;

import com.sun.mail.smtp.SMTPTransport;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import java.util.Date;
import java.util.Properties;

import static com.gyangod.constants.EmailConstant.*;
import static javax.mail.Message.RecipientType.CC;
import static javax.mail.Message.RecipientType.TO;

@Service
public class EmailService {
    private final Log log = LogFactory.getLog(EmailService.class);

    public void sendNewPasswordEmail(String firstName, String password, String email) throws MessagingException {
        Message message = createEmail(firstName, password, email);
        SMTPTransport smtpTransport = (SMTPTransport) getEmailSession().getTransport(SIMPLE_MAIL_TRANSFER_PROTOCOL);
        smtpTransport.connect(GODADDY_SMTP_SERVER, USERNAME, PASSWORD);
        log.info(PASSWORD);
        smtpTransport.sendMessage(message, message.getAllRecipients());
        smtpTransport.close();
    }

    private Session getEmailSession() {
        Properties properties = System.getProperties();
        properties.put(SMTP_HOST, GODADDY_SMTP_SERVER);
        properties.put(SMTP_AUTH, true);
        properties.put(SMTP_PORT, DEFAULT_PORT);
        properties.put(SMTP_STARTTLS_ENABLE, true);
        properties.put(SMTP_STARTTLS_REQUIRED, true);
        return Session.getInstance(properties, null);
    }

    private Message createEmail(String firstName, String password, String email) throws MessagingException {
        Message message = new MimeMessage(getEmailSession());
        message.setFrom(new InternetAddress(FROM_EMAIL));
        message.setRecipients(TO, InternetAddress.parse(email, false));
        message.setRecipients(CC, InternetAddress.parse(CC_EMAIL, false));
        message.setSubject(EMAIL_SUBJECT);
        message.setText("Hello " + firstName + ", \n \n Your new account password is: " + password + "\n \n The Support Team");
//        message.setContent("","text/html");
        message.setSentDate(new Date());
        message.saveChanges();
        return message;
    }
}
