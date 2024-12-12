package myy803.socialbookstore.services.profile;

import myy803.socialbookstore.datamodel.BookCategory;
import myy803.socialbookstore.datamodel.User;
import myy803.socialbookstore.formsdata.UserProfileDto;
import myy803.socialbookstore.mappers.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import myy803.socialbookstore.datamodel.UserProfile;

import java.util.List;
import java.util.Optional;

@Service
public class UserProfileServiceImpl implements UserProfileService {


    private final UserProfileMapper userProfileMapper;
    private final BookAuthorMapper bookAuthorMapper;
    private final BookCategoryMapper bookCategoryMapper;

    @Autowired
    public UserProfileServiceImpl(UserProfileMapper userProfileMapper,
                                  BookAuthorMapper bookAuthorMapper,
                                  BookCategoryMapper bookCategoryMapper) {
        this.userProfileMapper = userProfileMapper;
        this.bookAuthorMapper = bookAuthorMapper;
        this.bookCategoryMapper = bookCategoryMapper;
    }


    @Override
    public List<BookCategory> findAllBookCategories() {
        return bookCategoryMapper.findAll();
    }

    @Override
    public UserProfileDto findUser(String username) {
        Optional<UserProfile> optUserProfile = userProfileMapper.findById(username);
        UserProfileDto userProfileDto;
        UserProfile userProfile;
        if (optUserProfile.isPresent()) {
            userProfile = optUserProfile.get();
            userProfileDto = userProfile.buildProfileDto();
        } else {
            userProfileDto = new UserProfileDto();
            userProfileDto.setUsername(username);
        }
        return userProfileDto;
    }

    @Override
    public void saveUserProfile(String username, UserProfileDto userProfileDto) {
        Optional<UserProfile> optUserProfile = userProfileMapper.findById(username);

        UserProfile userProfile = optUserProfile.orElseGet(UserProfile::new);
        userProfileDto.buildUserProfile(userProfile, bookAuthorMapper, bookCategoryMapper);
        userProfileMapper.save(userProfile);
    }
}
