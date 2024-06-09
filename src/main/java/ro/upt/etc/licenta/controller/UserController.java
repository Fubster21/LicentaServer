package ro.upt.etc.licenta.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.upt.etc.licenta.repository.UserRepository;
import ro.upt.etc.licenta.repository.dto.UserResponseDTO;
import ro.upt.etc.licenta.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/all")
    public ResponseEntity<List<UserResponseDTO>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @PostMapping("/new")
    public ResponseEntity<UserResponseDTO> createUser(@RequestBody UserResponseDTO userResponseDTO) {
        return ResponseEntity.ok(userService.createUser(userResponseDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDTO> updateUser(@PathVariable Long id, @RequestBody UserResponseDTO userResponseDTO) {
        return ResponseEntity.ok(userService.updateUser(id, userResponseDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @GetMapping("/search")
    public ResponseEntity<List<UserResponseDTO>> searchUsers(@RequestParam String username) {
        return ResponseEntity.ok(userService.searchUsersByUsername(username));
    }
}
