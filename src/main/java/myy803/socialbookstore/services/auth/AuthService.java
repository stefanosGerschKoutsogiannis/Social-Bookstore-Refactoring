package myy803.socialbookstore.services.auth;

import myy803.socialbookstore.datamodel.User;

public interface AuthService {

    void saveUser(User user);
    boolean isUserPresent(User user);
    String authenticateUser();
}
