package myy803.socialbookstore.services.profile;

import myy803.socialbookstore.datamodel.BookCategory;
import myy803.socialbookstore.datamodel.User;
import myy803.socialbookstore.formsdata.UserProfileDto;
import myy803.socialbookstore.mappers.BookAuthorMapper;
import myy803.socialbookstore.mappers.BookCategoryMapper;
import myy803.socialbookstore.mappers.UserMapper;
import myy803.socialbookstore.mappers.UserProfileMapper;
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
public class UserProfileServiceImpl implements UserDetailsService, UserProfileService {

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    UserMapper userMapper;
    @Autowired
    UserProfileMapper userProfileMapper;
    @Autowired
    BookAuthorMapper bookAuthorMapper;
    @Autowired
    BookCategoryMapper bookCategoryMapper;



    @Override
    public void saveUser(User user) {
        String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        userMapper.save(user);
    }

    @Override
    public boolean isUserPresent(User user) {
        Optional<User> storedUser = userMapper.findByUsername(user.getUsername());
        return storedUser.isPresent();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userMapper.findByUsername(username).orElseThrow(
                ()-> new UsernameNotFoundException(
                        String.format("USER_NOT_FOUND", username)
                ));
    }

    @Override
    public String authenticateUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
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
