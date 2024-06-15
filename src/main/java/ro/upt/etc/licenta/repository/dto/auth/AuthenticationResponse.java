package ro.upt.etc.licenta.repository.dto.auth;

import lombok.Builder;
import lombok.Data;
import ro.upt.etc.licenta.repository.entity.UserRole;

@Data
@Builder
public class AuthenticationResponse {
    private String token;
    private String userId;
    private UserRole role;
}
