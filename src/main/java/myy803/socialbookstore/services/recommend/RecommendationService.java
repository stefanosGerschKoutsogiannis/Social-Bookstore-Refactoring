package myy803.socialbookstore.services.recommend;

import myy803.socialbookstore.formsdata.BookDto;
import myy803.socialbookstore.formsdata.RecommendationsDto;

import java.util.List;

public interface RecommendationService {

    public List<BookDto> recommendBooks(RecommendationsDto recommendationsDto, String username);
}
