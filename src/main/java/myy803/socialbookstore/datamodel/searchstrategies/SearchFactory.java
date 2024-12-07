package myy803.socialbookstore.datamodel.searchstrategies;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SearchFactory {

	private final ExactSearchStrategy exactSearchStrategy;
	private final ApproximateSearchStrategy approximateSearchStrategy;
	private final RandomSearchStrategy randomSearchStrategy;

	@Autowired
	public SearchFactory(ExactSearchStrategy exactSearchStrategy,
						 ApproximateSearchStrategy approximateSearchStrategy,
						 RandomSearchStrategy randomSearchStrategy) {
		this.exactSearchStrategy = exactSearchStrategy;
		this.approximateSearchStrategy = approximateSearchStrategy;
        this.randomSearchStrategy = randomSearchStrategy;
    }

	public SearchStrategy create(String searchStrategy) {
		if (searchStrategy.equals("Exact")) {
			return exactSearchStrategy;
		} else if (searchStrategy.equals("Approximate")) {
			return approximateSearchStrategy;
		}
		return randomSearchStrategy;
	}
}
