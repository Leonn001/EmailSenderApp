package br.ucsal.edu.componentemail;

import java.util.Properties;

import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import java.util.Scanner;

public class EmailSender {
    private static final String EMAIL_FROM = "leonmoreira124@gmail.com";
    private static String emailTo;
    private static String subject;
    private static String messageBody;
    private static final String APP_PASSWORD = "kmel fald rvzj xjun";
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws Exception {
        Message message = new MimeMessage(getEmailSession());
        message.setFrom(new InternetAddress(EMAIL_FROM));
        emailTo = getEmailTo();
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emailTo));
        subject = getSubject();
        message.setSubject(subject);
        messageBody = getmessageBody();
        message.setText(messageBody);
        Transport.send(message);
    }

    private static String getEmailTo() {
        System.out.println("Digite o email de destino");
        String emailTo = scanner.nextLine();
        return emailTo;
    }

    private static String getSubject() {
        System.out.println("Digite o assunto da mensagem");
        String subject = scanner.nextLine();
        return subject;
    }

    private static String getmessageBody() {
        System.out.println("Digite o corpo da mensagem");
        String corpo = scanner.nextLine();
        return corpo;
    }

    private static Session getEmailSession() {
        return Session.getInstance(getGmailProperties(), new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(EMAIL_FROM, APP_PASSWORD);
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
}
