package myy803.socialbookstore.formsdata;
 
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;

import myy803.socialbookstore.datamodel.BookAuthor;
import myy803.socialbookstore.datamodel.BookCategory;
import myy803.socialbookstore.datamodel.UserProfile;
import myy803.socialbookstore.mappers.BookAuthorMapper;
import myy803.socialbookstore.mappers.BookCategoryMapper;

@Component
public class UserProfileDto {

	private String username;
	private String fullName;
	private int age;
	private String favouriteBookAuthors;
	private String[] favouriteBookCategories;
	
	public UserProfileDto(String username, String fullName, int age, String favouriteBookAuthors,
			String[] favouriteBookCategories) {
		super();
		this.username = username;
		this.fullName = fullName;
		this.age = age;
		this.favouriteBookAuthors = favouriteBookAuthors;
		this.favouriteBookCategories = favouriteBookCategories;
	}

	public UserProfileDto() {
		super();
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public int getAge() {
		return age;
	}

	public String getFavouriteBookAuthors() {
		return favouriteBookAuthors;
	}

	public void setFavouriteBookAuthors(String favouriteBookAuthors) {
		this.favouriteBookAuthors = favouriteBookAuthors;
	}

	public String[] getFavouriteBookCategories() {
		return favouriteBookCategories;
	}

	@Override
	public String toString() {
		return "UserProfileDto [username=" + username + ", fullName=" + fullName + ", age=" + age
				+ ", favouriteBookAuthors=" + favouriteBookAuthors + ", favouriteBookCategories="
				+ Arrays.toString(favouriteBookCategories) + "]";
	}

	public void buildUserProfile(UserProfile userProfile, BookAuthorMapper bookAuthorMapper, BookCategoryMapper bookCategoriesMapper) {
		userProfile.clear();
		
		userProfile.setUsername(username);
		userProfile.setFullName(fullName);
		userProfile.setAge(age);
		
		String[] bookAuthorsNames = favouriteBookAuthors.split(",");
        for (String bookAuthorsName : bookAuthorsNames) {
            List<BookAuthor> bookAuthors = bookAuthorMapper.findByName(bookAuthorsName.trim());
            BookAuthor bookAuthor;
            if (bookAuthors.isEmpty()) {
                bookAuthor = new BookAuthor();
                bookAuthor.setName(bookAuthorsName.trim());
            } else
                bookAuthor = bookAuthors.get(0);

            userProfile.addBookAuthor(bookAuthor);
        }

        for (String favouriteBookCategory : favouriteBookCategories) {
            List<BookCategory> categories = bookCategoriesMapper.findByName(favouriteBookCategory);
            userProfile.addBookCategory(categories.get(0));
        }
	}
}
