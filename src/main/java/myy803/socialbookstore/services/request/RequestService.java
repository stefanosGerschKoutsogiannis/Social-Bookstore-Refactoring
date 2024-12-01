package myy803.socialbookstore.services.request;

import myy803.socialbookstore.formsdata.BookDto;
import myy803.socialbookstore.formsdata.UserProfileDto;

import java.util.List;

public interface RequestService {

    public void requestBook(String username, int bookId);
    public List<BookDto> findBookRequests(String username);
    public List<UserProfileDto> findRequestingUsersForBook(int bookId);
    public void acceptRequestForBook(String username, int bookId);
    public void deleteRequestForBook(int bookId);
}
