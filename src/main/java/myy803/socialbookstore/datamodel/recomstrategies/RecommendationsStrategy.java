package myy803.socialbookstore.datamodel.recomstrategies;

import myy803.socialbookstore.datamodel.UserProfile;
import myy803.socialbookstore.formsdata.BookDto;
import myy803.socialbookstore.mappers.UserProfileMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public abstract class RecommendationsStrategy {

	private final UserProfileMapper userProfileMapper;

	@Autowired
	public RecommendationsStrategy(UserProfileMapper userProfileMapper) {
		this.userProfileMapper = userProfileMapper;
	}

	abstract List<BookDto> retrieveRecommendedBooks(UserProfile userProfile);

	public List<BookDto> recommend(String username) {
		UserProfile userProfile = userProfileMapper.findByUsername(username);
		return  retrieveRecommendedBooks(userProfile);
	}
}
