package com.gyangod.constants;

import org.springframework.beans.factory.annotation.Value;

public class EmailConstant {
    public static final String SIMPLE_MAIL_TRANSFER_PROTOCOL = "smtps";
    public static final String USERNAME = "support@gyangod.com";
    //todo: take from YAML file
    @Value("${email.custom-password}")
    public static String PASSWORD = "Amar@12345";
    public static final String FROM_EMAIL = "no-reply@gyangod.com";
    public static final String CC_EMAIL = "";
    public static final String EMAIL_SUBJECT = "Gyangod - Reset Password";
    /*public static final String IMAP_SERVER = "imap.secureserver.net";
    public static final int IMAP_PORT = 993;*/
    public static final String GODADDY_SMTP_SERVER = "smtpout.secureserver.net";
    public static final String SECURITY_TYPE = "SSL";
    public static final String SMTP_HOST = "mail.smtp.host";
    public static final String SMTP_AUTH = "mail.smtp.auth";
    public static final String SMTP_PORT = "mail.smtp.port";
    public static final int DEFAULT_PORT = 465;
    public static final String SMTP_STARTTLS_ENABLE = "mail.smtp.starttls.enable";
    public static final String SMTP_STARTTLS_REQUIRED = "mail.smtp.starttls.required";
}
