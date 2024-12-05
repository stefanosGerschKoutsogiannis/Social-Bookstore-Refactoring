package myy803.socialbookstore.services.email;

import myy803.socialbookstore.formsdata.UserProfileDto;

import java.util.List;

public interface EmailService {

    void sendConformationMessageToUser(String to, String subject, String text);
    void sendMessageToUsersThatTheBookWasTaken(List<UserProfileDto> users, String subject, String text);
}
