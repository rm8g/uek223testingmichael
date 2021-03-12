package ch.noser.uek223.core.security.authentication;

import ch.noser.uek223.domain.user.dto.UserDTOMinimal;

import java.util.UUID;

public interface AuthenticationService {

    UserDTOMinimal getAuthenticationResponse(UUID userId);

    void authenticate(String authenticationHeader);

}
