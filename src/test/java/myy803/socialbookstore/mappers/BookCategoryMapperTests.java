package myy803.socialbookstore.mappers;

import myy803.socialbookstore.datamodel.BookAuthor;
import myy803.socialbookstore.datamodel.BookCategory;
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
public class BookCategoryMapperTests {

    @Autowired
    BookCategoryMapper bookCategoryMapper;

    private BookCategory bookCategoryTest;

    @BeforeEach
    public void setUpData() {
        bookCategoryTest = new BookCategory();
        bookCategoryTest.setName("Fake category");
    }

    @Test
    @DisplayName("Junit test for save operation")
    void givenBookCategory_whenSave_thenSuccess() {
        BookCategory savedBookCategory = bookCategoryMapper.save(bookCategoryTest);

        assertThat(savedBookCategory).isNotNull();
        assertThat(savedBookCategory.getCategoryId()).isGreaterThan(0);
    }

    @Test
    @DisplayName("Junit test for find by name operation")
    void givenBookCategory_whenFindByName_thenSuccess() {
        BookCategory savedBookCategory = bookCategoryMapper.save(bookCategoryTest);

        List<BookCategory> bookCategories = bookCategoryMapper.findByName(savedBookCategory.getName());
        Assertions.assertNotNull(bookCategories);
        assertThat(bookCategories.size()).isEqualTo(1);
        Assertions.assertEquals(savedBookCategory.getCategoryId(), bookCategories.get(0).getCategoryId());
        Assertions.assertEquals(savedBookCategory.getName(), bookCategories.get(0).getName());
    }

}
