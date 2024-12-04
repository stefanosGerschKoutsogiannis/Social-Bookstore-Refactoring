package myy803.socialbookstore.mappers;

import myy803.socialbookstore.datamodel.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserMapperTests {

    @Autowired
    UserMapper userMapper;

    private User testUser;

    @BeforeEach
    public void setUpData() {
        testUser = new User();
        testUser.setUsername("WizardOfOz");
    }

    @Test
    @DisplayName("Junit test for save operation")
    public void givenUser_whenSave_thenSuccess() {
        User savedUser = userMapper.save(testUser);

        Assertions.assertNotNull(savedUser);
    }

    @Test
    public void givenUser_whenFindByUsername_thenSuccess() {
        User savedUser = userMapper.save(testUser);

        Optional<User> user = userMapper.findByUsername(savedUser.getUsername());
        Assertions.assertTrue(user.isPresent());
        Assertions.assertEquals(savedUser.getUsername(), user.get().getUsername());
    }
}
