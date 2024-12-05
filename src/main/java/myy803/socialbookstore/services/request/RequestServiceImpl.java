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
    public Book acceptRequestForBook(String username, int bookId) {
        Optional<Book> requestedBook = findBookById(bookId);
        Optional<UserProfile> userProfile = findUserProfileById(username);
        requestedBook.get().removeRequestingUser(userProfile.get());

        bookMapper.delete(requestedBook.get());

        return requestedBook.get();
    }

    // DOES NOT WORK CORRECTLY, delete my book requests, WHY DOES IT NOT WORK
    @Override
    public void deleteRequestForBook(String username, int bookId) {
        Optional<UserProfile> userProfile = findUserProfileById(username);
        Optional<Book> requestedBook = findBookById(bookId);
        requestedBook.get().removeRequestingUser(userProfile.get());
        userProfile.get().removeBookFromRequestedBooks(requestedBook.get());

        bookMapper.save(requestedBook.get());
        userProfileMapper.save(userProfile.get());

        System.err.println(requestedBook.get().getRequestingUsers());
    }
}
