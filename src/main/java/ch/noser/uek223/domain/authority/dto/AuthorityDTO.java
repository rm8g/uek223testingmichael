package ch.noser.uek223.domain.authority.dto;

import java.util.UUID;

public class AuthorityDTO {

    private UUID id;
    private String name;

    public AuthorityDTO(UUID id, String name) {
        this.id = id;
        this.name = name;
    }

    public AuthorityDTO() {
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
}
