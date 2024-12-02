package myy803.socialbookstore.services.search;

import myy803.socialbookstore.datamodel.searchstrategies.SearchFactory;
import myy803.socialbookstore.datamodel.searchstrategies.SearchStrategy;
import myy803.socialbookstore.formsdata.BookDto;
import myy803.socialbookstore.formsdata.SearchDto;
import myy803.socialbookstore.mappers.BookMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.naming.directory.SearchResult;
import java.util.List;

@Service
public class SearchServiceImpl implements SearchService {


    private final SearchFactory searchFactory;
    private final BookMapper bookMapper;

    @Autowired
    public SearchServiceImpl(SearchFactory searchFactory, BookMapper bookMapper) {
        this.searchFactory = searchFactory;
        this.bookMapper = bookMapper;
    }

    @Override
    public List<BookDto> searchBooks(SearchDto searchDto) {
        SearchStrategy searchStrategy = searchFactory.create(searchDto.getSelectedStrategy());
        return searchStrategy.search(searchDto, bookMapper);
    }
}
