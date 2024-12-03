package myy803.socialbookstore.datamodel.searchstrategies;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SearchFactory {

	private final ExactSearchStrategy exactSearchStrategy;
	private final ApproximateSearchStrategy approximateSearchStrategy;

	@Autowired
	public SearchFactory(ExactSearchStrategy exactSearchStrategy, ApproximateSearchStrategy approximateSearchStrategy) {
		this.exactSearchStrategy = exactSearchStrategy;
		this.approximateSearchStrategy = approximateSearchStrategy;
	}
	
	public SearchStrategy create(String searchStrategy) {
		if(searchStrategy.equals("Exact"))
			return exactSearchStrategy;
		else
			return approximateSearchStrategy;
	}
}
