package myy803.socialbookstore.formsdata;

import java.util.ArrayList;

public class RecommendationsDto {
	private final ArrayList<String> recommendationStrategies;
	private String selectedStrategy;
	
	public RecommendationsDto() {
		super();
		recommendationStrategies = new ArrayList<String>();
		recommendationStrategies.add("Favourite Categories"); 
		recommendationStrategies.add("Favourite Authors");
		recommendationStrategies.add("Both");
	}

	public ArrayList<String> getRecommendationStrategies() {
		return recommendationStrategies;
	}

	public String getSelectedStrategy() {
		return selectedStrategy;
	}
}
