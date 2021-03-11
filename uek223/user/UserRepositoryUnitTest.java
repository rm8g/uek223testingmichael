package ch.noser.uek223.user;

import ch.noser.uek223.domain.authority.Authority;
import ch.noser.uek223.domain.role.Role;
import ch.noser.uek223.domain.user.User;
import ch.noser.uek223.domain.user.UserRepository;
import org.aspectj.lang.annotation.Before;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@DataJpaTest
@TestPropertySource("classpath:application-test.properties")
public class UserRepositoryUnitTest {
    @Autowired
    private TestEntityManager testEntityManager;
    @Autowired
    private UserRepository userRepository;
    private User userToBeTestedAgainst1;
    private User userToBeTestedAgainst2;
    private User newUserToBeSaved;
    private List<User> listOfUsersToBeTestedAgainst;
    String userIdFromDBUser1;
    String userIdFromDBUser2;
    List<String> listOfIdsFromDB;

    @Before("")
    public void setUp() {
        Set<Authority> authoritiesToBeTestedAgainst = Stream.of(new Authority().setName("USER_SEE"), new Authority().setName("USER_CREATE"), new Authority().setName("USER_MODIFY"), new Authority().setName("USER_DELETE")).collect(Collectors.toSet());
        Set<Role> rolesToBeTestedAgainst = Stream.of(new Role().setName("BASIC_USER").setAuthorities(authoritiesToBeTestedAgainst)).collect(Collectors.toSet());
        userToBeTestedAgainst1 = new User().setFirstName("John").setSurname("Doe").setEmail("john.doe@noseryoung.ch").setPasswordHash(new BCryptPasswordEncoder().encode(UUID.randomUUID().toString())).setRoles(rolesToBeTestedAgainst);
        userToBeTestedAgainst2 = new User().setFirstName("Jane").setSurname("Doe").setEmail("jane.doe@noseryoung.ch").setPasswordHash(new BCryptPasswordEncoder().encode(UUID.randomUUID().toString())).setRoles(rolesToBeTestedAgainst);
        newUserToBeSaved = new User().setFirstName("Jack").setSurname("Doe").setEmail("jack.doe@noseryoung.ch").setPasswordHash(new BCryptPasswordEncoder().encode(UUID.randomUUID().toString())).setRoles(rolesToBeTestedAgainst);
        userIdFromDBUser1 = testEntityManager.persistAndGetId(userToBeTestedAgainst1, String.class);
        userIdFromDBUser2 = testEntityManager.persistAndGetId(userToBeTestedAgainst2, String.class);
        listOfUsersToBeTestedAgainst = Arrays.asList(userToBeTestedAgainst1, userToBeTestedAgainst2);
        listOfIdsFromDB = Arrays.asList(userIdFromDBUser1, userIdFromDBUser2);
    }

    @Test
    public void findById_requestUserById_returnsUser() {
        Optional<User> userFromDB = userRepository.findById(UUID.fromString(userIdFromDBUser1));
        Assertions.assertThat(userFromDB).isEqualTo(Optional.ofNullable(userToBeTestedAgainst1));
        Assertions.assertThat(userFromDB.get().getId()).isEqualTo(userIdFromDBUser1);
        Assertions.assertThat(userFromDB.get().getFirstName()).isEqualTo(userToBeTestedAgainst1.getFirstName());
        Assertions.assertThat(userFromDB.get().getSurname()).isEqualTo(userToBeTestedAgainst1.getSurname());
        Assertions.assertThat(userFromDB.get().getEmail()).isEqualTo(userToBeTestedAgainst1.getEmail());
        Assertions.assertThat(userFromDB.get().getPasswordHash()).isEqualTo(userToBeTestedAgainst1.getPasswordHash());
        Assertions.assertThat(userFromDB.get().getRoles()).isEqualTo(userToBeTestedAgainst1.getRoles());
    }
}