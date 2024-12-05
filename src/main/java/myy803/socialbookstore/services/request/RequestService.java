package myy803.socialbookstore.services.request;

import myy803.socialbookstore.datamodel.Book;
import myy803.socialbookstore.formsdata.BookDto;
import myy803.socialbookstore.formsdata.UserProfileDto;

import java.util.List;

public interface RequestService {

    void requestBook(String username, int bookId);
    List<BookDto> findBookRequests(String username);
    List<UserProfileDto> findRequestingUsersForBook(int bookId);
    Book acceptRequestForBook(String username, int bookId);
    void deleteRequestForBook(String username, int bookId);
}
