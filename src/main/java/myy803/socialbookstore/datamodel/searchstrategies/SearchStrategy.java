package myy803.socialbookstore.datamodel.searchstrategies;

import java.util.ArrayList;
import java.util.List;

import myy803.socialbookstore.datamodel.Book;
import myy803.socialbookstore.formsdata.BookDto;
import myy803.socialbookstore.formsdata.SearchDto;
import myy803.socialbookstore.mappers.BookMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public abstract class SearchStrategy {

	private final BookMapper bookMapper;

	@Autowired
	public SearchStrategy(BookMapper bookMapper) {
		this.bookMapper = bookMapper;
	}

	protected abstract List<Book> makeInitialListOfBooks(SearchDto searchDto);
	protected abstract boolean checkIfAuthorsMatch(SearchDto searchDto, Book book);


	public ArrayList<BookDto> search(SearchDto searchDto, BookMapper bookMapper) {
		ArrayList<BookDto> bookDtos = new ArrayList<BookDto>();

		if(searchDto.getTitle() != null) {
			List<Book> books = makeInitialListOfBooks(searchDto);

			boolean authorsMatch = true;

			for(Book book : books) {
				if(!searchDto.getAuthors().equals("")) {
					authorsMatch = checkIfAuthorsMatch(searchDto, book);
				}
				if(authorsMatch) bookDtos.add(book.buildDto());
			}
		}

		return bookDtos;
	}

	public BookMapper getBookMapper() {
		return bookMapper;
	}
}
