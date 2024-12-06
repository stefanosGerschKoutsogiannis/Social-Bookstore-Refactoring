package myy803.socialbookstore.datamodel.searchstrategies;

import myy803.socialbookstore.datamodel.Book;
import myy803.socialbookstore.formsdata.BookDto;
import myy803.socialbookstore.formsdata.SearchDto;
import myy803.socialbookstore.mappers.BookMapper;

import java.util.ArrayList;
import java.util.List;

public interface SearchStrategy {


    ArrayList<BookDto> search(SearchDto searchDto, BookMapper bookMapper);

}
