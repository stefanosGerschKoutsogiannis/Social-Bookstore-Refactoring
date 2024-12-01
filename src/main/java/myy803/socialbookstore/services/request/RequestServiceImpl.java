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

    @Autowired
    BookMapper bookMapper;

    @Autowired
    UserProfileMapper userProfileMapper;



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
        Optional<Book> requestedBook = findBookById(bookId);
        Optional<UserProfile> userProfile = findUserProfileById(username);
        requestedBook.get().removeRequestingUser(userProfile.get());
        bookMapper.delete(requestedBook.get());
    }

    // works
    @Override
    public void deleteRequestForBook(int bookId) {
        Optional<Book> requestedBook = findBookById(bookId);
        bookMapper.delete(requestedBook.get());
    }
}
