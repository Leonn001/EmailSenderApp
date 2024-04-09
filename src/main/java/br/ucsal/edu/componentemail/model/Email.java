package br.ucsal.edu.componentemail.model;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/*
A classe de Email serve para ser um modelo de email, os atributos dessa classe representam o que é necessário para um
email ser enviado, como destinatários, remetente, assunto, corpo de mensagem, a senha de APP do google
*/
public class Email {
    private String emailFrom;
    private String passwordEmailFrom;
    private List<String> emailsTo = new ArrayList<>();
    private String subject;
    private String messageBody;
    // Aqui nós criamos uma thread para agilizar o envio de multiplos emails
    private ExecutorService executor = Executors.newFixedThreadPool(10);

    /*
    Este método realiza o envio de emails criando a mensagem com os atributos da classe email
    e utilizando a API jakarta.mail para enviar os emails.
    */
    public void sendEmails() {
        for (String currentEmail : emailsTo) {
            executor.execute(() -> {
                try {
                    Message message = new MimeMessage(getEmailSession());
                    message.setFrom(new InternetAddress(emailFrom));
                    message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(currentEmail));
                    message.setSubject(subject);
                    message.setText(messageBody);
                    Transport.send(message);
                    System.out.println("Email enviado com sucesso para " + currentEmail);
                } catch (MessagingException e) {
                    System.out.println("Erro ao enviar o email para " + currentEmail + ": " + e.getMessage());
                }
            });
        }
        executor.shutdown();
    }

    /*
    Este método cria e retorna uma instância da classe Session do JavaMail, que é usada para configurar a sessão
    de e-mail para enviar e-mails usando um servidor SMTP do Gmail. Ele usa as propriedades específicas do Gmail
    e uma instância de Authenticator para autenticação com o servidor SMTP do Gmail, usando o endereço de e-mail
    e a senha fornecidos.
    */
    private Session getEmailSession(){
        return Session.getInstance(getGmailProperties(), new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(emailFrom, passwordEmailFrom);
            }
        });
    }

    /*
    Este método retorna um objeto Properties que contém as configurações específicas do servidor SMTP do Gmail
    para enviar e-mails usando JavaMail.
    */
    private static Properties getGmailProperties() {
        Properties prop = new Properties();
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true");
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        return prop;
    }

    /*
    Aqui nós temos um construtor vazio, como nada é informado ele tem uma mensagem padrão que envia um email
    do remetente de testes para o destinatário email de testes.
     */
    public Email() {
        this.emailsTo.add("emailsendert82@gmail.com");
        this.emailFrom = "emailsendert82@gmail.com";
        this.passwordEmailFrom = "bipk eeot jjsm fvqy";
        this.subject = "Hello!";
        this.messageBody = "Hello test email, how are you?";
    }

    /*
    Aqui nós temos um construtor com todos os campos informados, esse construtor recebe uma lista
    de emails destinatários  e adiciona esses emails ao atributo de
    emailsTo (que é a lista de emails destinatários que irá ser passada pro sendEmails)
    */
    public Email(String emailFrom, String passwordEmailFrom, List<String> emailsTo, String subject, String messageBody) {
        this.emailFrom = emailFrom;
        this.passwordEmailFrom = makePasswordRegular(passwordEmailFrom);
        this.emailsTo = emailsTo;
        this.subject = subject;
        this.messageBody = messageBody;
    }

    /*
    Esse construtor serve para quando não for informado um email remetente, vamos utilizar um email como padrão
    para que a mensagem seja enviada.
     */
    public Email(List<String> emailsTo, String subject, String messageBody) {
        this.emailFrom = "emailsendert82@gmail.com";
        this.passwordEmailFrom = "bipk eeot jjsm fvqy";
        this.emailsTo = emailsTo;
        this.subject = subject;
        this.messageBody = messageBody;
    }

    /*
    Este método serve para validar a senha esperada pela API (xxxx xxxx xxxx xxxx)
     */
    public static String makePasswordRegular(String password) throws IllegalArgumentException {
        password = password.toLowerCase();

        // Remove todos os caracteres não alfabéticos
        password = password.replaceAll("[^a-z]", "");

        // Se o comprimento da senha for menor que 16 caracteres, lança uma exceção
        if (password.length() < 16) {
            throw new IllegalArgumentException("A senha fornecida é fora do padrao esperado. Espera-se 16 caracteres alfabeticos");
        }

        // Divide a senha em partes de 4 caracteres
        StringBuilder regularPassword = new StringBuilder();
        for (int i = 0; i < password.length(); i += 4) {
            regularPassword.append(password.substring(i, Math.min(i + 4, password.length())));
            regularPassword.append(" ");
        }

        // Remove o espaço extra no final
        return regularPassword.toString().trim();
    }

    /*
    Métodos getters e setters
     */
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

    public List<String> getEmailsTo() {
        return emailsTo;
    }

    public void setEmailsTo(List<String> emailsTo) {
        this.emailsTo = emailsTo;
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
