package myy803.socialbookstore.mappers;

import myy803.socialbookstore.datamodel.BookAuthor;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class BookAuthorMapperTests {

    @Autowired
    BookAuthorMapper bookAuthorMapper;

    private BookAuthor russianGuy;

    @BeforeEach
    public void setUpTestData() {
        russianGuy = new BookAuthor("Fyodor Dostoevsky");
    }

    @Test
    @DisplayName("Junit test for save book author operation")
    void givenBookAuthor_whenSave_thenReturnSaveBookAuthor() {
        BookAuthor savedBookAuthor = bookAuthorMapper.save(russianGuy);

        assertThat(savedBookAuthor).isNotNull();
        assertThat(savedBookAuthor.getAuthorId()).isGreaterThan(0);
    }

    @Test
    @DisplayName("Junit test for find by name operation")
    void givenBookAuthor_whenFindByName_thenReturnBookAuthor() {
        BookAuthor rus = bookAuthorMapper.save(russianGuy);

        List<BookAuthor> authors = bookAuthorMapper.findByName(russianGuy.getName());
        Assertions.assertNotNull(authors);
        assertThat(authors.size()).isEqualTo(1);
        Assertions.assertEquals(rus.getAuthorId(), authors.get(0).getAuthorId());
        Assertions.assertEquals(rus.getName(), authors.get(0).getName());
    }

}
