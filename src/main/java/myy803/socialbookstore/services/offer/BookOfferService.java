package myy803.socialbookstore.services.offer;

import myy803.socialbookstore.datamodel.BookCategory;
import myy803.socialbookstore.formsdata.BookDto;

import java.util.List;

public interface BookOfferService {

    List<BookDto> findBookOffers(String username);
    List<BookCategory> showBookOffers();
    void addBookOffer(String username, BookDto bookOfferDto);
    void deleteBookOffer(int bookDtoId);
}
