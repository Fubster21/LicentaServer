package ro.upt.etc.licenta.repository.dto.auth;

import lombok.Data;

@Data
public class RegisterRequest {
    private String username;
    private String password;
//    private String email;
    private String phone;
    private String address;
    private String firstName;
    private String lastName;
}
