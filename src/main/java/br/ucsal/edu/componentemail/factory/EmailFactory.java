package br.ucsal.edu.componentemail.factory;

import br.ucsal.edu.componentemail.model.Email;

public class EmailFactory {

    public Email createRegisterEmail(String emailFrom, String passwordEmailFrom, String emailTo) {
        String subject = "Confirmação de Cadastro";
        String messageBody = "Olá, Obrigado por se cadastrar! Atenciosamente, EmailSender";
        return new Email(emailFrom,passwordEmailFrom,emailTo, subject, messageBody);
    }

    public Email createPasswordRecoveryEmail(String emailFrom, String passwordEmailFrom, String emailTo) {
        String subject = "Recuperação de Senha";
        String messageBody = "Olá, Você solicitou a recuperação de sua senha! Atenciosamente, EmailSender";
        return new Email(emailTo, subject, messageBody);
    }

    public Email createAccountUpdateEmail(String emailFrom, String passwordEmailFrom, String emailTo) {
        String subject = "Atualização de Conta";
        String messageBody = "Olá, Suas informações de conta foram atualizadas com sucesso! Atenciosamente, EmailSender";
        return new Email(emailTo, subject, messageBody);
    }

    public Email createOrderNotificationEmail(String emailFrom, String passwordEmailFrom, String emailTo) {
        String subject = "Confirmação de Pedido";
        String messageBody = "Olá, Seu pedido foi recebido com sucesso! Atenciosamente, EmailSender";
        return new Email(emailTo, subject, messageBody);
    }
}
