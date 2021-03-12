package ch.noser.uek223.domain.role.dto;

import ch.noser.uek223.domain.authority.dto.AuthorityDTO;

import java.util.Set;
import java.util.UUID;

public class RoleDTOMinimal {

    private UUID id;
    private String name;
    private Set<AuthorityDTO> authorities;

    public RoleDTOMinimal(UUID id, String name, Set<AuthorityDTO> authorities) {
        this.id = id;
        this.name = name;
        this.authorities = authorities;
    }

    public RoleDTOMinimal() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<AuthorityDTO> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<AuthorityDTO> authorities) {
        this.authorities = authorities;
    }
}
