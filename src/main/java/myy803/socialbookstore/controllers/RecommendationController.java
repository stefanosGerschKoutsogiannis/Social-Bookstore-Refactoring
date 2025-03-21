package myy803.socialbookstore.controllers;

import myy803.socialbookstore.formsdata.BookDto;
import myy803.socialbookstore.formsdata.RecommendationsDto;
import myy803.socialbookstore.services.auth.AuthService;
import myy803.socialbookstore.services.recommend.RecommendationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class RecommendationController {

    private final AuthService authService;
    private final RecommendationService recommendationService;

    @Autowired
    public RecommendationController(AuthService authService,
                                    RecommendationService recommendationService) {
        this.authService = authService;
        this.recommendationService = recommendationService;
    }


    @RequestMapping("/user/recom")
    public String showRecommendationsForm(Model model) {
        RecommendationsDto recommendationsDto = new RecommendationsDto();
        model.addAttribute("recomDto", recommendationsDto);

        return "user/recommendation_form";
    }

    @RequestMapping("/user/recommend_offers")
    public String recommend(@ModelAttribute("recomDto") RecommendationsDto recommendationsDto, Model model) {
        String username = authService.authenticateUser();
        recommendationService.recommendBooks(recommendationsDto, username);
        List<BookDto> bookDtos = recommendationService.recommendBooks(recommendationsDto, username);

        model.addAttribute("books", bookDtos);

        return "user/search_results";
    }
}
