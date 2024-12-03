package myy803.socialbookstore.datamodel.recomstrategies;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RecommendationsFactory {

	private final FavouriteCategoriesStrategy favouriteCategoriesStrategy;
	private final FavouriteAuthorsStrategy favouriteAuthorsStrategy;
	private final FavouriteCategoriesAndAuthorsStrategy favouriteCategoriesAndAuthorsStrategy;

	@Autowired
	public RecommendationsFactory(FavouriteAuthorsStrategy favouriteAuthorsStrategy,
								  FavouriteCategoriesStrategy favouriteCategoriesStrategy,
								  FavouriteCategoriesAndAuthorsStrategy favouriteCategoriesAndAuthorsStrategy) {
		this.favouriteCategoriesStrategy = favouriteCategoriesStrategy;
		this.favouriteAuthorsStrategy = favouriteAuthorsStrategy;
		this.favouriteCategoriesAndAuthorsStrategy = favouriteCategoriesAndAuthorsStrategy;
	}
	
	
	public RecommendationsStrategy create(String recommendationStrategy) {
		if(recommendationStrategy.equals("Favourite Categories"))
			return favouriteCategoriesStrategy;
		else
			if(recommendationStrategy.equals("Favourite Authors"))
				return favouriteAuthorsStrategy;
			else 
				return favouriteCategoriesAndAuthorsStrategy;
	}

}
