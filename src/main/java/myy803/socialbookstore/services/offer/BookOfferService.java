package myy803.socialbookstore.services.offer;

import myy803.socialbookstore.datamodel.BookCategory;
import myy803.socialbookstore.formsdata.BookDto;

import java.util.List;

public interface BookOfferService {

    public List<BookDto> findBookOffers(String username);
    public List<BookCategory> showBookOffers();
    public void addBookOffer(String username, BookDto bookOfferDto);
    public void deleteBookOffer(String username, int bookDtoId);
}
