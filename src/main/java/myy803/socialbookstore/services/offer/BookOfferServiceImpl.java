package myy803.socialbookstore.services.offer;

import myy803.socialbookstore.datamodel.BookCategory;
import myy803.socialbookstore.datamodel.UserProfile;
import myy803.socialbookstore.formsdata.BookDto;
import myy803.socialbookstore.mappers.BookAuthorMapper;
import myy803.socialbookstore.mappers.BookCategoryMapper;
import myy803.socialbookstore.mappers.BookMapper;
import myy803.socialbookstore.mappers.UserProfileMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookOfferServiceImpl implements BookOfferService {


    private final UserProfileMapper userProfileMapper;
    private final BookCategoryMapper bookCategoryMapper;
    private final BookAuthorMapper bookAuthorMapper;
    private final BookMapper bookMapper;

    @Autowired
    public BookOfferServiceImpl(UserProfileMapper userProfileMapper,
                                BookCategoryMapper bookCategoryMapper,
                                BookAuthorMapper bookAuthorMapper,
                                BookMapper bookMapper) {
        this.userProfileMapper = userProfileMapper;
        this.bookCategoryMapper = bookCategoryMapper;
        this.bookAuthorMapper = bookAuthorMapper;
        this.bookMapper = bookMapper;
    }

    @Override
    public List<BookDto> findBookOffers(String username) {
        Optional<UserProfile> optUserProfile = userProfileMapper.findById(username);
        return optUserProfile.get().buildBookOffersDtos();
    }

    @Override
    public List<BookCategory> showBookOffers() {
        return bookCategoryMapper.findAll();
    }

    @Override
    public void addBookOffer(String username, BookDto bookOfferDto) {
        Optional<UserProfile> optUserProfile = userProfileMapper.findById(username);
        UserProfile userProfile = optUserProfile.get();
        userProfile.addBookOffer(bookOfferDto.buildBookOffer(bookAuthorMapper, bookCategoryMapper));
        userProfileMapper.save(userProfile);
    }

    /*
    does not work, fixed it
     */
    @Override
    public void deleteBookOffer(int bookDtoId) {
        bookMapper.deleteById(bookDtoId);
    }
}
