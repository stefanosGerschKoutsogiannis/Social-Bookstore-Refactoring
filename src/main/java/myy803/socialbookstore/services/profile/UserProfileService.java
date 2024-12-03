package myy803.socialbookstore.services.profile;

import myy803.socialbookstore.datamodel.BookCategory;
import myy803.socialbookstore.datamodel.User;
import myy803.socialbookstore.formsdata.UserProfileDto;

import java.util.List;

public interface UserProfileService {
    void saveUser(User user);
    boolean isUserPresent(User user);
    String authenticateUser();
    List<BookCategory> findAllBookCategories();
    UserProfileDto findUser(String username);
    void saveUserProfile(String username, UserProfileDto userProfileDto);

}
