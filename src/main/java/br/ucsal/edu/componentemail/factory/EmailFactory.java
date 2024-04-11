package br.ucsal.edu.componentemail.factory;

import br.ucsal.edu.componentemail.model.Email;

import java.util.List;
import java.util.Set;

/*
Esta classe serve para criar emails com assuntos e corpo de texto prontos
Aqui você vai instânciar uma classe Email e os atributos (emailFrom,passwordEmailFrom e emailTo) vão ser passados
por parametro
Esta classe não será instanciada, apenas serve para chamar os métodos que instanciar Emails.
*/
public class  EmailFactory {

    public static Email createRegisterEmail(String emailFrom, String passwordEmailFrom, Set<String> emailTo) {
        String subject = "Confirmação de Cadastro";
        String messageBody = "Olá, Obrigado por se cadastrar! Atenciosamente, EmailSender";
        return new Email(emailFrom,passwordEmailFrom,emailTo, subject, messageBody);
    }

    public static Email createPasswordRecoveryEmail(String emailFrom, String passwordEmailFrom, Set<String> emailTo) {
        String subject = "Recuperação de Senha";
        String messageBody = "Olá, Você solicitou a recuperação de sua senha! Atenciosamente, EmailSender";
        return new Email(emailFrom,passwordEmailFrom,emailTo, subject, messageBody);
    }

    public static Email createAccountUpdateEmail(String emailFrom, String passwordEmailFrom, Set<String> emailTo) {
        String subject = "Atualização de Conta";
        String messageBody = "Olá, Suas informações de conta foram atualizadas com sucesso! Atenciosamente, EmailSender";
        return new Email(emailFrom,passwordEmailFrom,emailTo, subject, messageBody);
    }

    public static Email createOrderNotificationEmail(String emailFrom, String passwordEmailFrom, Set<String> emailTo) {
        String subject = "Confirmação de Pedido";
        String messageBody = "Olá, Seu pedido foi recebido com sucesso! Atenciosamente, EmailSender";
        return new Email(emailFrom,passwordEmailFrom,emailTo, subject, messageBody);
    }
}
