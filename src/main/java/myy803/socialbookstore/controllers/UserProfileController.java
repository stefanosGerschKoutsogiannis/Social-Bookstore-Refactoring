package myy803.socialbookstore.controllers;

import myy803.socialbookstore.datamodel.BookCategory;
import myy803.socialbookstore.formsdata.UserProfileDto;
import myy803.socialbookstore.mappers.BookCategoryMapper;
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

    @Autowired
    UserProfileService userProfileService;

    @RequestMapping("/user/dashboard")
    public String getUserMainMenu(){

        return "user/dashboard";
    }

    @RequestMapping("/user/profile")
    public String retrieveUserProfile(Model model){
        String username = userProfileService.authenticateUser();
        System.err.println("Logged use: " + username);

        // create a service, for books or something
        List<BookCategory> categories = bookCategoryMapper.findAll();
        model.addAttribute("categories", categories);

        // method at userProfileService
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
