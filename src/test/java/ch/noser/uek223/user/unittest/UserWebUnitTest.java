package ch.noser.uek223.user.unittest;

import ch.noser.uek223.domain.authority.Authority;
import ch.noser.uek223.domain.role.Role;
import ch.noser.uek223.domain.user.User;
import ch.noser.uek223.domain.user.UserServiceImpl;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.*;
import java.util.stream.Stream;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserWebUnitTest {

    @MockBean
    private UserServiceImpl userService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser(username = "tester", password = "password", roles = "")
    public void findAll_requestAllUsers_returnsAllUsers() throws Exception {
//        User user1 = new User(UUID.randomUUID(), "cs@mail.com", "", "Chris", "South", Set.of(new Role().setName("ADMIN").setAuthorities(Set.of(new Authority().setName("CAN_RETRIEVE_ALL_PRODUCTS")))), null, null);
//        User user2 = new User(UUID.randomUUID(), "aa@mail.com", "", "Anna", "Abby", Set.of(new Role().setName("CUSTOMER").setAuthorities(Set.of(new Authority().setName("CAN_DELETE_USER")))), null, null);


        User user1 = new User().setId(UUID.randomUUID()).setEmail("cs@mail.com").setPasswordHash("").setFirstName("Chris").setSurname("South").setRoles(Set.of(new Role().setName("ADMIN").setAuthorities(Set.of(new Authority().setName("CAN_RETRIEVE_ALL_PRODUCTS")))));
        User user2 = new User().setId(UUID.randomUUID()).setEmail("aa@mail.com").setPasswordHash("").setFirstName("Anna").setSurname("Abby").setRoles(Set.of(new Role().setName("CUSTOMER").setAuthorities(Set.of(new Authority().setName("CAN_DELETE_USER")))));

        List<User> usersToBeTestedAgainst = new ArrayList<>();
        usersToBeTestedAgainst.add(user1);
        usersToBeTestedAgainst.add(user2);

        given(userService.findAll()).willReturn(usersToBeTestedAgainst);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/users")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[*].id").value(Matchers.containsInAnyOrder(user1.getId().toString(), user2.getId().toString())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[*].firstName").value(Matchers.containsInAnyOrder(user1.getFirstName(), user2.getFirstName())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[*].lastName").value(Matchers.containsInAnyOrder(user1.getSurname(), user2.getSurname())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[*].email").value(Matchers.containsInAnyOrder(user1.getEmail(), user2.getEmail())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[*].roles[*].name").value(Matchers.containsInAnyOrder(Stream.concat(user1.getRoles().stream().map(Role::getName), user2.getRoles().stream().map(Role::getName)).toArray())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[*].roles[*].authorities[*].name").value(Matchers.containsInAnyOrder(Stream.concat(user1.getRoles().stream().map(Role::getAuthorities).flatMap(Collection::stream).map(Authority::getName), user2.getRoles().stream().map(Role::getAuthorities).flatMap(Collection::stream).map(Authority::getName)).toArray())));
        verify(userService, times(1)).findAll();
    }
}