package myy803.socialbookstore.controllers;

import myy803.socialbookstore.datamodel.BookCategory;
import myy803.socialbookstore.formsdata.BookDto;
import myy803.socialbookstore.services.offer.BookOfferService;
import myy803.socialbookstore.services.profile.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class BookOfferController {

    @Autowired
    UserProfileService userProfileService;

    @Autowired
    BookOfferService bookOfferService;

    @RequestMapping("/user/offers")
    public String listBookOffers(Model model) {
        String username = userProfileService.authenticateUser();
        List<BookDto> bookOffersDtos = bookOfferService.findBookOffers(username);

        model.addAttribute("offers", bookOffersDtos);

        return "user/offers";
    }

    @RequestMapping("/user/show_offer_form")
    public String showOfferForm(Model model) {
        List<BookCategory> categories = bookOfferService.showBookOffers();
        model.addAttribute("categories", categories);
        model.addAttribute("offer", new BookDto());

        return "user/offer-form";
    }

    @RequestMapping("/user/save_offer")
    public String saveBookOffer(@ModelAttribute("offer") BookDto bookOfferDto, Model model) {
        String username = userProfileService.authenticateUser();
        bookOfferService.addBookOffer(username, bookOfferDto);

        return "redirect:/user/offers";
    }

    @RequestMapping("/user/delete_book_offer")
    public String deleteBookOffer(@RequestParam("selected_offer_id") int bookId, Model model) {
        String username = userProfileService.authenticateUser();
        bookOfferService.deleteBookOffer(username, bookId);

        System.err.println("Delete Book Offer for book id: " + bookId);

        return "redirect:/user/dashboard";
    }

}
