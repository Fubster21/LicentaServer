package ro.upt.etc.licenta.service;

import ro.upt.etc.licenta.repository.dto.UserResponseDTO;
import java.util.List;

public interface UserService {
    List<UserResponseDTO> getAllUsers();
    UserResponseDTO createUser(UserResponseDTO userResponseDTO);
    UserResponseDTO updateUser(Long id, UserResponseDTO userResponseDTO);
    void deleteUser(Long id);
    UserResponseDTO getUserById(Long id);
    List<UserResponseDTO> searchUsersByUsername(String username);
}
