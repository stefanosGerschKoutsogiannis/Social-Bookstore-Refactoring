package myy803.socialbookstore.mappers;

import myy803.socialbookstore.datamodel.UserProfile;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserProfileMapperTests {

    @Autowired
    private UserProfileMapper userProfileMapper;

    private UserProfile testUserProfile;

    @BeforeEach
    public void setUpData() {
        testUserProfile = new UserProfile();
        testUserProfile.setUsername("WizardOfOz");
    }

    @Test
    @DisplayName("Junit test for save operation")
    public void givenUserProfile_whenSave_thenSuccess() {
        UserProfile savedUserProfile = userProfileMapper.save(testUserProfile);

        Assertions.assertNotNull(savedUserProfile);
    }

    @Test
    @DisplayName("Junit test for find by username operation")
    public void givenUserProfile_whenFindByUsername_thenSuccess() {
        UserProfile savedUserProfile = userProfileMapper.save(testUserProfile);

        UserProfile foundUserProfile = userProfileMapper.findByUsername(testUserProfile.getUsername());
        Assertions.assertNotNull(foundUserProfile);
        Assertions.assertEquals(testUserProfile.getUsername(), foundUserProfile.getUsername());
    }
}
