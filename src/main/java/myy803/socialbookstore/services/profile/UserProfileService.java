package myy803.socialbookstore.services.profile;

import myy803.socialbookstore.datamodel.User;
import myy803.socialbookstore.formsdata.UserProfileDto;

public interface UserProfileService {
    public void saveUser(User user);
    public boolean isUserPresent(User user);
    public String authenticateUser();
    public UserProfileDto findUser(String username);
    public void saveUserProfile(String username, UserProfileDto userProfileDto);
}
