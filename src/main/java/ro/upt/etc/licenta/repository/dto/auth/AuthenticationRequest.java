package ro.upt.etc.licenta.repository.dto.auth;

import lombok.Data;

@Data
public class AuthenticationRequest {
    private String username;
    private String password;
}
