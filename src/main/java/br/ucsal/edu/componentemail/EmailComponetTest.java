package br.ucsal.edu.componentemail;

import br.ucsal.edu.componentemail.factory.EmailFactory;
import br.ucsal.edu.componentemail.model.Email;

import java.util.Set;
import java.util.TreeSet;

public class EmailComponetTest {
    public static void main(String[] args) {
        String emailFrom = "emailsendert82@gmail.com";
        String passwordEmailFrom = "biPkeeoTjjsmfvqy";

        Set<String> emailsTo = new TreeSet<>();

        emailsTo.add("luiseduardo.santos@ucsal.edu.br");
        emailsTo.add("luma.ceciliaa@ucsal.edu.br");
        emailsTo.add("gustavoalves.andrade@ucsal.edu.br");

        String subject = ("Boa noite testando envio de emails");
        String messageBody = ("to testando isso mas ja cansei disso");

        Email emailCreateAccountUpdateEmail = EmailFactory.createAccountUpdateEmail(emailFrom,passwordEmailFrom,emailsTo);

        Email emailsSend = new Email(emailFrom,passwordEmailFrom, emailsTo,subject,messageBody);

        emailsSend.addEmail("leon.moreira@ucsal.edu.br");
        emailsSend.removeEmail("leon.moreira@ucsal.edu.br");
        emailsSend.editEmail("luma.ceciliaa@ucsal.edu.br","luma.cecilia@ucsal.edu.br");

        emailsSend.sendEmails();
        emailCreateAccountUpdateEmail.sendEmails();

    }
}
