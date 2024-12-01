package myy803.socialbookstore.controllers;

import myy803.socialbookstore.formsdata.BookDto;
import myy803.socialbookstore.formsdata.UserProfileDto;
import myy803.socialbookstore.services.request.RequestService;
import myy803.socialbookstore.services.profile.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class RequestController {

    @Autowired
    UserProfileService userProfileService;

    @Autowired
    RequestService requestService;

    @RequestMapping("/user/request_book")
    public String requestBook(@RequestParam("selected_book_id") int bookId, Model model) {
        String username = userProfileService.authenticateUser();
        requestService.requestBook(username, bookId);

        return "redirect:/user/dashboard";
    }

    @RequestMapping("/user/requests")
    public String showUserBookRequests(Model model) {
        String username = userProfileService.authenticateUser();
        List <BookDto> requests = requestService.findBookRequests(username);
        model.addAttribute("requests", requests);

        return "/user/book_requests";
    }

    @RequestMapping("/user/book_requesting_users")
    public String showRequestingUsersForBookOffer(@RequestParam("selected_offer_id") int bookId, Model model) {
        List<UserProfileDto> requestingUsers = requestService.findRequestingUsersForBook(bookId);

        model.addAttribute("requesting_users", requestingUsers);
        model.addAttribute("book_id", bookId);

        return "/user/requesting_users";
    }

    @RequestMapping("/user/accept_request")
    public String acceptBookRequest(@RequestParam("selected_user") String username, @RequestParam("book_id") int bookId, Model model) {
        requestService.acceptRequestForBook(username, bookId);
        System.err.println("Selected user: " + username + " for book id: " + bookId);

        return "redirect:/user/dashboard";
    }

    @RequestMapping("/user/delete_book_request")
    public String deleteBookRequest(@RequestParam("selected_request_id") int bookId, Model model) {
        requestService.deleteRequestForBook(bookId);
        System.err.println("Delete Book Request for book id: " + bookId);

        return "redirect:/user/dashboard";
    }
}
