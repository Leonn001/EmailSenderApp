package br.ucsal.edu.componentemail;

import br.ucsal.edu.componentemail.model.Email;
import br.ucsal.edu.componentemail.factory.EmailFactory;

public class EmailSenderTest {

    public static void main(String[] args) {

        String emailFrom = "emailsendert82@gmail.com";
        String passwordEmailFrom = "bipk eeot jjsm fvqy";
        String emailTo = "emailsendert82@gmail.com";
        String subject = "Assunto teste";
        String messageBody = " testando os envios de emails";

        //Testando o EmailFactory
        EmailFactory emailFactory = new EmailFactory();
        Email emailRegister = emailFactory.createRegisterEmail(emailFrom,passwordEmailFrom,emailTo);

        //Testando envio padrão sem nenhum campo informada
        Email emailConstructorNoFields = new Email();

        //Testando envio de email com todos os campos informados
        Email emailConstructorAllFields = new Email(emailFrom,passwordEmailFrom,emailTo,subject,messageBody);

        //emailRegister.sendEmail();
        //emailConstructorNoFields.sendEmail();
        //emailConstructorAllFields.sendEmail();

    }
}
