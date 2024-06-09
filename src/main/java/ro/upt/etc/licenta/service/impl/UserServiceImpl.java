package ro.upt.etc.licenta.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.upt.etc.licenta.repository.UserRepository;
import ro.upt.etc.licenta.repository.dto.UserResponseDTO;
import ro.upt.etc.licenta.repository.entity.User;
import ro.upt.etc.licenta.service.UserService;
import org.modelmapper.ModelMapper;
import ro.upt.etc.licenta.service.exception.ResourceNotFoundException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<UserResponseDTO> getAllUsers() {
        return userRepository.findAll().stream()
                .map(user -> modelMapper.map(user, UserResponseDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public UserResponseDTO createUser(UserResponseDTO userResponseDTO) {
        User user = modelMapper.map(userResponseDTO, User.class);
        user = userRepository.save(user);
        return modelMapper.map(user, UserResponseDTO.class);
    }

    @Override
    public UserResponseDTO updateUser(Long id, UserResponseDTO userResponseDTO) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id " + id));
        modelMapper.map(userResponseDTO, existingUser);
        existingUser = userRepository.save(existingUser);
        return modelMapper.map(existingUser, UserResponseDTO.class);
    }

    @Override
    public void deleteUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id " + id));
        userRepository.delete(user);
    }

    @Override
    public UserResponseDTO getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id " + id));
        return modelMapper.map(user, UserResponseDTO.class);
    }

    @Override
    public List<UserResponseDTO> searchUsersByUsername(String username) {
        return userRepository.findByUsernameContainingIgnoreCase(username).stream()
                .map(user -> modelMapper.map(user, UserResponseDTO.class))
                .collect(Collectors.toList());
    }
}
