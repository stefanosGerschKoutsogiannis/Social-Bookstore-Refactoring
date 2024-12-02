package myy803.socialbookstore.services.request;

import myy803.socialbookstore.datamodel.Book;
import myy803.socialbookstore.datamodel.UserProfile;
import myy803.socialbookstore.formsdata.BookDto;
import myy803.socialbookstore.formsdata.UserProfileDto;
import myy803.socialbookstore.mappers.BookMapper;
import myy803.socialbookstore.mappers.UserProfileMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RequestServiceImpl implements RequestService {

    private final BookMapper bookMapper;
    private final UserProfileMapper userProfileMapper;

    @Autowired
    public RequestServiceImpl(BookMapper bookMapper, UserProfileMapper userProfileMapper) {
        this.bookMapper = bookMapper;
        this.userProfileMapper = userProfileMapper;
    }

    @Override
    public void requestBook(String username, int bookId) {
        Optional<Book> requestedBook = findBookById(bookId);
        Optional<UserProfile> userProfile = findUserProfileById(username);
        requestedBook.get().addRequestingUser(userProfile.get());
        bookMapper.save(requestedBook.get());
    }

    private Optional<Book> findBookById(int bookId) {
        return bookMapper.findById(bookId);
    }

    private Optional<UserProfile> findUserProfileById(String userProfileId) {
        return userProfileMapper.findById(userProfileId);
    }

    @Override
    public List<BookDto> findBookRequests(String username) {
        Optional<UserProfile> userProfile = findUserProfileById(username);
        return userProfile.get().buildBookRequestsDtos();
    }

    @Override
    public List<UserProfileDto> findRequestingUsersForBook(int bookId) {
        Optional<Book> book = findBookById(bookId);
        return book.get().getRequestingUserProfileDtos();
    }

    // accept a users request and delete requests from other users, works, add notification
    @Override
    public void acceptRequestForBook(String username, int bookId) {
        // find the requested book
        Optional<Book> requestedBook = findBookById(bookId);
        // find the user that is requesting the book
        Optional<UserProfile> userProfile = findUserProfileById(username);
        // get the book and remove requesting user
        requestedBook.get().removeRequestingUser(userProfile.get());

        //notify user that he has taken the book

        // delete the book from the database because it has been taken
        bookMapper.delete(requestedBook.get());
    }

    // DOES NOT WORK CORRECTLY
    @Override
    public void deleteRequestForBook(int bookId) {
        // find the book
        Optional<Book> requestedBook = findBookById(bookId);
        // delete the book from the database instead of deleting the request of the user
        //bookMapper.delete(requestedBook.get());
    }
}
