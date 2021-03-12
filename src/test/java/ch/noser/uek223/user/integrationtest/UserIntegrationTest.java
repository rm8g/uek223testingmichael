package ch.noser.uek223.user.integrationtest;

import ch.noser.uek223.domain.authority.Authority;
import ch.noser.uek223.domain.authority.AuthorityRepository;
import ch.noser.uek223.domain.authority.dto.AuthorityDTO;
import ch.noser.uek223.domain.role.Role;
import ch.noser.uek223.domain.role.RoleRepository;
import ch.noser.uek223.domain.role.dto.RoleDTOMinimal;
import ch.noser.uek223.domain.user.User;
import ch.noser.uek223.domain.user.UserMapper;
import ch.noser.uek223.domain.user.UserRepository;
import ch.noser.uek223.domain.user.dto.UserDTOMinimal;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.springframework.test.annotation.DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = AFTER_EACH_TEST_METHOD)
@TestPropertySource("classpath:application-test.properties")
public class UserIntegrationTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private AuthorityRepository authorityRepository;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser(username = "tester", password = "password")
    public void findById_requestUserById_returnsUser() throws Exception {
        UUID uuidToBeTestedAgainst = UUID.randomUUID();

        Set<Authority> authoritiesToBeTestedAgainst = Stream.of(new Authority().setName("CAN_READ_ALL_PRODUCTS"), new Authority().setName("CAN_READ_PRODUCT"), new Authority().setName("CAN_UPDATE_PURCHASE"), new Authority().setName("CAN_DELETE_USER")).collect(Collectors.toSet());
        authorityRepository.saveAll(authoritiesToBeTestedAgainst);

        Set<Role> rolesToBeTestedAgainst = Stream.of(new Role().setName("CUSTOMER").setAuthorities(authoritiesToBeTestedAgainst)).collect(Collectors.toSet());
        roleRepository.saveAll(rolesToBeTestedAgainst);

        List<User> usersToBeTestedAgainst = (List<User>) Stream.of(new User().setFirstName("John").setSurname("Tester").setEmail("jt@testmail.com").setPasswordHash(new BCryptPasswordEncoder().encode(uuidToBeTestedAgainst.randomUUID().toString())).setRoles(rolesToBeTestedAgainst));
        User user = new User().setFirstName("John").setSurname("Tester").setEmail("jt@testmail.com").setPasswordHash(new BCryptPasswordEncoder().encode(uuidToBeTestedAgainst.randomUUID().toString())).setRoles(rolesToBeTestedAgainst);
        usersToBeTestedAgainst.add(user);
        userRepository.saveAll(usersToBeTestedAgainst);
        UserDTOMinimal userDTOToBeTestedAgainst = (UserDTOMinimal) userMapper.usersToUsersDTOMinimal(usersToBeTestedAgainst);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/users/{id}", userDTOToBeTestedAgainst.getId())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(userDTOToBeTestedAgainst.getId().toString()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName").value(userDTOToBeTestedAgainst.getFirstName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.roles[*].name").value(Matchers.containsInAnyOrder(userDTOToBeTestedAgainst.getRoles().stream().map(RoleDTOMinimal::getName).toArray())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.roles[*].authorities[*].name").value(Matchers.containsInAnyOrder(userDTOToBeTestedAgainst.getRoles().stream().map(RoleDTOMinimal::getAuthorities).flatMap(Collection::stream).map(AuthorityDTO::getName).toArray())));
    }
}