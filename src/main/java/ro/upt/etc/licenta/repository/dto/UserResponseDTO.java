package ro.upt.etc.licenta.repository.dto;

import lombok.Data;

@Data
public class UserResponseDTO {
    private Long id;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String address;
}
