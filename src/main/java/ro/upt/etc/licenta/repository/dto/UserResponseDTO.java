package ro.upt.etc.licenta.repository.dto;

import lombok.*;
import ro.upt.etc.licenta.repository.entity.UserRole;

@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class UserResponseDTO {
    private Long id;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String address;
    private UserRole role;
}
