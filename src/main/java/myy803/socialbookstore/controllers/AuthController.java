package myy803.socialbookstore.controllers;


import myy803.socialbookstore.services.profile.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import myy803.socialbookstore.datamodel.User;


@Controller
public class AuthController {
    @Autowired
    UserProfileService userProfileService;

    @RequestMapping("/login")
    public String login(){
        return "auth/login";
    }

    @RequestMapping("/register")
    public String register(Model model){
        model.addAttribute("user", new User());
        return "auth/register";
    }

    @RequestMapping("/save")
    public String registerUser(@ModelAttribute("user") User user, Model model){
       
        if(userProfileService.isUserPresent(user)){
            model.addAttribute("successMessage", "User already registered!");
            return "auth/login";
        }

        userProfileService.saveUser(user);
        model.addAttribute("successMessage", "User registered successfully!");

        return "auth/login";
    }
}
