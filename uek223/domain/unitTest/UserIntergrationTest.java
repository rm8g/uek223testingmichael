/*
package ch.noser.uek223.domain.unitTest;

import ch.noser.uek223.domain.authority.Authority;
import ch.noser.uek223.domain.authority.dto.AuthorityDTO;
import ch.noser.uek223.domain.role.Role;
import ch.noser.uek223.domain.role.dto.RoleDTOMinimal;
import ch.noser.uek223.domain.user.User;
import ch.noser.uek223.domain.user.UserRepository;
import ch.noser.uek223.domain.user.dto.UserDTOMinimal;
import ch.noser.uek223.domain.user.dto.UserDTONew;
import org.aspectj.lang.annotation.Before;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Collection;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.springframework.test.annotation.DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-test.properties")
@DirtiesContext(classMode = AFTER_EACH_TEST_METHOD)
public class UserIntegrationTest {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MockMvc mvc;
    @Before("")
    public void setUp(){}
    @Test
    public void findById_requestUserById_returnsUser() throws Exception {
        UUID uuidToBeTestedAgainst = UUID.randomUUID();
        Set<Authority> authoritiesToBeTestedAgainst = Stream.of(new Authority().setName("USER_SEE"), new Authority().setName("USER_CREATE"), new Authority().setName("USER_MODIFY"), new Authority().setName("USER_DELETE")).collect(Collectors.toSet());
        Set<Role> rolesToBeTestedAgainst = Stream.of(new Role().setName("BASIC_USER").setAuthorities(authoritiesToBeTestedAgainst)).collect(Collectors.toSet());
        User userToBeTestedAgainst = new User().setFirstName("John").setSurname("Doe").setEmail("john.doe@noseryoung.ch").setPasswordHash(new BCryptPasswordEncoder().encode(uuidToBeTestedAgainst.randomUUID().toString())).setRoles(rolesToBeTestedAgainst);
        userRepository.save(userToBeTestedAgainst);
        Set<AuthorityDTO> authorityDTOSToBeTestedAgainst = Stream.of(new AuthorityDTO().setName("USER_SEE"), new AuthorityDTO().setName("USER_CREATE"), new AuthorityDTO().setName("USER_MODIFY"), new AuthorityDTO().setName("USER_DELETE")).collect(Collectors.toSet());
        Set<RoleDTOMinimal> roleDTOSToBeTestedAgainst = Stream.of(new RoleDTOMinimal().setName("BASIC_USER").setAuthorities(authorityDTOSToBeTestedAgainst)).collect(Collectors.toSet());
        UserDTONew userDTOToBeTestedAgainst = new UserDTONew(userToBeTestedAgainst.getId()).setFirstName("John").setLastName("Doe").setEmail("john.doe@noseryoung.ch").setRoles(roleDTOSToBeTestedAgainst);
        mvc.perform(
                MockMvcRequestBuilders.get("/users/{id}", userDTOToBeTestedAgainst.getId())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(userDTOToBeTestedAgainst.getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName").value(userDTOToBeTestedAgainst.getFirstName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.lastName").value(userDTOToBeTestedAgainst.getSurname()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value(userDTOToBeTestedAgainst.getEmail()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.roles[*].name").value(Matchers.containsInAnyOrder(userDTOToBeTestedAgainst.getRoles().stream().map(RoleDTOMinimal::getName).toArray())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.roles[*].authorities[*].name").value(Matchers.containsInAnyOrder(userDTOToBeTestedAgainst.getRoles().stream().map(RoleDTOMinimal::getAuthorities).flatMap(Collection::stream).map(AuthorityDTO::getName).toArray())));
    }
}*/
