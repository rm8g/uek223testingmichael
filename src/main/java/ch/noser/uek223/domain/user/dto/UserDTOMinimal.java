package ch.noser.uek223.domain.user.dto;

import ch.noser.uek223.domain.role.dto.RoleDTOMinimal;

import java.util.Set;
import java.util.UUID;

public class UserDTOMinimal {

    private UUID id;
    private String firstName;
    private Set<RoleDTOMinimal> roles;

    public UserDTOMinimal(UUID id, String firstName, Set<RoleDTOMinimal> roles) {
        this.id = id;
        this.firstName = firstName;
        this.roles = roles;
    }

    public UserDTOMinimal() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Set<RoleDTOMinimal> getRoles() {
        return roles;
    }

    public void setRoles(Set<RoleDTOMinimal> roles) {
        this.roles = roles;
    }
}
