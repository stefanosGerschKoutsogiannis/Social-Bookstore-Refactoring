package myy803.socialbookstore.datamodel.recomstrategies;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RecommendationsFactory {

	@Autowired
	private FavouriteCategoriesStrategy favouriteCategoriesStrategy;
	@Autowired
	private FavouriteAuthorsStrategy favouriteAuthorsStrategy;
	@Autowired
	private FavouriteCategoriesAndAuthorsStrategy favouriteCategoriesAndAuthorsStrategy;
	
	
	public RecommendationsStrategy create(String recomStrategy) {
		if(recomStrategy.equals("Favourite Categories"))
			return favouriteCategoriesStrategy;
		else
			if(recomStrategy.equals("Favourite Authors"))
				return favouriteAuthorsStrategy;
			else 
				return favouriteCategoriesAndAuthorsStrategy;
	}

}
