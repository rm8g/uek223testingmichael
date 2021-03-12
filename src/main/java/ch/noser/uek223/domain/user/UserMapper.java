package ch.noser.uek223.domain.user;

import ch.noser.uek223.domain.user.dto.UserDTOMinimal;
import ch.noser.uek223.domain.user.dto.UserDTONew;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    List<UserDTOMinimal> usersToUsersDTOMinimal(List<User> users);
    UserDTOMinimal toDTO(User user);
    User userDTONewToUser(UserDTONew user);
    User userDTOMinimalToUser(UserDTOMinimal user);
}
