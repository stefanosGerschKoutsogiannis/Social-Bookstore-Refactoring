package myy803.socialbookstore.services.search;

import myy803.socialbookstore.formsdata.BookDto;
import myy803.socialbookstore.formsdata.SearchDto;

import java.util.List;

public interface SearchService {

    List<BookDto> searchBooks(SearchDto searchDto);
}
