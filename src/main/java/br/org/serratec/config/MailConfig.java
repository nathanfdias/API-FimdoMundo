package br.org.serratec.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

@Configuration
public class MailConfig {

    @Autowired
    private JavaMailSender javaMailSender;

    public void sendEmail(String clienteEmail, String assunto, String texto) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("nathan.dias@aluno.senai.br");
        message.setTo(clienteEmail);
        message.setSubject(assunto);
        message.setText(texto);
        message.setText("Email:\n" + texto + "\n\n\n\n" + "Serratec Residência-2022");
        javaMailSender.send(message);
    }
}
