package br.ucsal.edu.componentemail.model;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import java.util.Properties;

public class Email {
    private  String emailFrom;
    private  String passwordEmailFrom;
    private  String emailTo;
    private  String subject;
    private  String messageBody;

    public void sendEmail() {
        try {
            Message message = new MimeMessage(getEmailSession());
            message.setFrom(new InternetAddress(emailFrom));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emailTo));
            message.setSubject(subject);
            message.setText(messageBody);
            Transport.send(message);
            System.out.println("Email enviado com sucesso para " + emailTo);
        } catch (MessagingException e) {
            System.out.println("Erro ao enviar o email: " + e.getMessage());
        }
    }

    private Session getEmailSession(){
        return Session.getInstance(getGmailProperties(), new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(emailFrom, passwordEmailFrom);
            }
        });
    }

    private static Properties getGmailProperties() {
        Properties prop = new Properties();
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true");
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        return prop;
    }

    public Email() {
        this.emailTo = "emailsendert82@gmail.com";
        this.emailFrom = "emailsendert82@gmail.com";
        this.passwordEmailFrom = "bipk eeot jjsm fvqy";
        this.subject = "Hello!";
        this.messageBody = "Hello test email, how are you?";
    }

    public Email(String emailFrom, String passwordEmailFrom, String emailTo, String subject, String messageBody) {
        this.emailFrom = emailFrom;
        this.passwordEmailFrom = passwordEmailFrom;
        this.emailTo = emailTo;
        this.subject = subject;
        this.messageBody = messageBody;
    }

    public Email(String emailTo, String subject, String messageBody) {
        this.emailFrom = "emailsendert82@gmail.com";
        this.passwordEmailFrom = "bipk eeot jjsm fvqy";
        this.emailTo = emailTo;
        this.subject = subject;
        this.messageBody = messageBody;
    }

    public String getEmailFrom() {
        return emailFrom;
    }

    public void setEmailFrom(String emailFrom) {
        this.emailFrom = emailFrom;
    }

    public String getPasswordEmailFrom() {
        return passwordEmailFrom;
    }

    public void setPasswordEmailFrom(String passwordEmailFrom) {
        this.passwordEmailFrom = passwordEmailFrom;
    }

    public String getEmailTo() {
        return emailTo;
    }

    public void setEmailTo(String emailTo) {
        this.emailTo = emailTo;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessageBody() {
        return messageBody;
    }

    public void setMessageBody(String messageBody) {
        this.messageBody = messageBody;
    }
}
