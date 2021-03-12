package ch.noser.uek223.domain.role;

import ch.noser.uek223.domain.role.dto.RoleDTOMinimal;
import org.mapstruct.Mapper;

import java.util.Set;

@Mapper(componentModel = "spring")
public interface RoleMapper {

    RoleDTOMinimal roleToRoleDTOMinimal(Role role);
    Set<RoleDTOMinimal> rolesToRolesDTOMinimal(Set<Role> roles);

}
