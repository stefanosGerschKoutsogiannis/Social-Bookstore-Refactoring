package myy803.socialbookstore.datamodel.recomstrategies;

import myy803.socialbookstore.formsdata.BookDto;

import java.util.List;

public interface RecommendationsStrategy {

    List<BookDto> recommend(String username);
}
