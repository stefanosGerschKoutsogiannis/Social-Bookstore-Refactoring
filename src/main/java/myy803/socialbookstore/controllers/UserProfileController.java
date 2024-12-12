package myy803.socialbookstore.controllers;

import myy803.socialbookstore.datamodel.BookCategory;
import myy803.socialbookstore.formsdata.UserProfileDto;
import myy803.socialbookstore.mappers.BookCategoryMapper;
import myy803.socialbookstore.services.auth.AuthService;
import myy803.socialbookstore.services.profile.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class UserProfileController {

    @Autowired
    BookCategoryMapper bookCategoryMapper;

    private final UserProfileService userProfileService;
    public final AuthService authService;

    @Autowired
    public UserProfileController(UserProfileService userProfileService,
                                 AuthService authService) {
        this.userProfileService = userProfileService;
        this.authService = authService;
    }

    @RequestMapping("/user/dashboard")
    public String getUserMainMenu(){

        return "user/dashboard";
    }

    @RequestMapping("/user/profile")
    public String retrieveUserProfile(Model model){
        String username = authService.authenticateUser();
        System.err.println("Logged use: " + username);

        List<BookCategory> categories = userProfileService.findAllBookCategories();
        model.addAttribute("categories", categories);

        UserProfileDto userProfileData =  userProfileService.findUser(username);
        model.addAttribute("profile", userProfileData);

        return "user/profile";
    }

    @RequestMapping("/user/save_profile")
    public String saveUserProfile(@ModelAttribute("profile") UserProfileDto userProfileDto, Model theModel) {
        System.err.println(userProfileDto.toString());

        userProfileService.saveUserProfile(userProfileDto.getUsername(), userProfileDto);

        return "user/dashboard";
    }
}
