package myy803.socialbookstore.services.email;

import myy803.socialbookstore.formsdata.UserProfileDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender emailSender;

    @Autowired
    public EmailServiceImpl(JavaMailSender emailSender) {
        this.emailSender = emailSender;
    }

    @Override
    public void sendConformationMessageToUser(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("noreply@cs05046.com");
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        emailSender.send(message);
    }

    @Override
    public void sendMessageToUsersThatTheBookWasTaken(List<UserProfileDto> users, String subject, String text) {
        for (UserProfileDto user: users) {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("noreply@socialbookstore.com");
            message.setTo(user.getUsername());
            message.setSubject(subject);
            message.setText(text);
            emailSender.send(message);
        }
    }

}
