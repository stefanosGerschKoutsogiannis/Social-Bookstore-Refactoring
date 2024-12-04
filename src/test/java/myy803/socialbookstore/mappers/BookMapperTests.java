package myy803.socialbookstore.mappers;

import myy803.socialbookstore.datamodel.Book;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class BookMapperTests {

    @Autowired
    BookMapper bookMapper;

    private Book testBook;

    @BeforeEach
    public void setUpData() {
        testBook = new Book("Crime and Punishment");
    }

    @Test
    @DisplayName("Junit test for save book operation")
    public void givenBook_whenSaveBook_thenSuccess() {
        Book savedBook = bookMapper.save(testBook);

        assertThat(savedBook).isNotNull();
        assertThat(savedBook.getOfferId()).isGreaterThan(0);
    }

    @Test
    @DisplayName("Junit test for find by title operation")
    public void givenBook_whenFindByTitle_thenReturnBook() {
        Book savedBook = bookMapper.save(testBook);

        List<Book> books = bookMapper.findByTitle(savedBook.getTitle());
        Assertions.assertNotNull(books);
        assertThat(books.size()).isEqualTo(1);
        Assertions.assertEquals(savedBook.getOfferId(), books.get(0).getOfferId());
        Assertions.assertEquals(savedBook.getTitle(), books.get(0).getTitle());
    }

    @Test
    @DisplayName("Junit test for find by title containing operation")
    public void givenBook_whenFindByTitleContaining_thenReturnBook() {
        Book savedBook = bookMapper.save(testBook);

        List<Book> books = bookMapper.findByTitleContaining(savedBook.getTitle());
        Assertions.assertNotNull(books);
        assertThat(books.size()).isEqualTo(1);
        Assertions.assertEquals(savedBook.getOfferId(), books.get(0).getOfferId());
        Assertions.assertEquals(savedBook.getTitle(), books.get(0).getTitle());
    }
}
