package myy803.socialbookstore.services.recommend;

import myy803.socialbookstore.datamodel.recomstrategies.RecommendationsFactory;
import myy803.socialbookstore.datamodel.recomstrategies.RecommendationsStrategy;
import myy803.socialbookstore.formsdata.BookDto;
import myy803.socialbookstore.formsdata.RecommendationsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecommendationServiceImpl implements RecommendationService {

    private final RecommendationsFactory recommendationsFactory;

    @Autowired
    public RecommendationServiceImpl(RecommendationsFactory recommendationsFactory) {
        this.recommendationsFactory = recommendationsFactory;
    }

    @Override
    public List<BookDto> recommendBooks(RecommendationsDto recommendationsDto, String username) {
        RecommendationsStrategy recommendationsStrategy = recommendationsFactory.create(
                recommendationsDto.getSelectedStrategy());
        return recommendationsStrategy.recommend(username);
    }
}
