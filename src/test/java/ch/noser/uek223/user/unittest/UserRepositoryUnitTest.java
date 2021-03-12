package ch.noser.uek223.user.unittest;

import ch.noser.uek223.domain.user.User;
import ch.noser.uek223.domain.user.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.annotation.DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = AFTER_EACH_TEST_METHOD)
@TestPropertySource("classpath:application-test.properties")
public class UserRepositoryUnitTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    public void save_saveNewUser_savedUser() throws Exception {
        User user = new User();
        user.setFirstName("Test First Name").setSurname("Test Last Name").setEmail("Test Email").setPasswordHash("Test Password");

        User userActual = userRepository.saveAndFlush(user);

        user.setId(userActual.getId());

        assertThat(user).isEqualToComparingFieldByField(userActual);
    }
}