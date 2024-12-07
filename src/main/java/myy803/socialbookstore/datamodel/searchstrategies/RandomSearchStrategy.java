package myy803.socialbookstore.datamodel.searchstrategies;

import myy803.socialbookstore.formsdata.BookDto;
import myy803.socialbookstore.formsdata.SearchDto;
import myy803.socialbookstore.mappers.BookMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class RandomSearchStrategy implements SearchStrategy {

    @Override
    public ArrayList<BookDto> search(SearchDto searchDto, BookMapper bookMapper) {
        ArrayList<BookDto> bookDtos = new ArrayList<>();
        bookDtos.add(bookMapper.findRandomEntity().get().buildDto());

        return bookDtos;
    }



}
