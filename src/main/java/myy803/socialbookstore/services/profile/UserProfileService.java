package myy803.socialbookstore.services.profile;

import myy803.socialbookstore.datamodel.BookCategory;
import myy803.socialbookstore.datamodel.User;
import myy803.socialbookstore.formsdata.UserProfileDto;

import java.util.List;

public interface UserProfileService {
    public void saveUser(User user);
    public boolean isUserPresent(User user);
    public String authenticateUser();
    public List<BookCategory> findAllBookCategories();
    public UserProfileDto findUser(String username);
    public void saveUserProfile(String username, UserProfileDto userProfileDto);

}
