package myy803.socialbookstore.services.profile;

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

import java.util.Optional;

@Service
public class UserProfileServiceImpl implements UserDetailsService, UserProfileService {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserProfileMapper userProfileMapper;

    @Autowired
    private BookAuthorMapper bookAuthorMapper;

    @Autowired
    private BookCategoryMapper bookCategoryMapper;

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

    public String authenticateUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }

    public UserProfileDto findUser(String username) {
        Optional<UserProfile> optUserProfile = userProfileMapper.findById(username);
        UserProfileDto userProfileDto = null;
        UserProfile userProfile = null;
        if (optUserProfile.isPresent()) {
            userProfile = optUserProfile.get();
            userProfileDto = userProfile.buildProfileDto();
        } else {
            userProfileDto = new UserProfileDto();
            userProfileDto.setUsername(username);
        }
        return userProfileDto;
    }

    public void saveUserProfile(String username, UserProfileDto userProfileDto) {
        Optional<UserProfile> optUserProfile = userProfileMapper.findById(username);

        UserProfile userProfile = optUserProfile.orElseGet(UserProfile::new);
        userProfileDto.buildUserProfile(userProfile, bookAuthorMapper, bookCategoryMapper);
        userProfileMapper.save(userProfile);
    }
}
