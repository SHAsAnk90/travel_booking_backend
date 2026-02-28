package com.travelbooking.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.travelbooking.repository.UserRepository;
import com.travelbooking.dto.request.UserRequestDTO;
import com.travelbooking.dto.response.UserResponseDTO;
import com.travelbooking.entity.User;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserResponseDTO createUser(UserRequestDTO userRequest)
    {
        if(userRepository.existsByEmail(userRequest.getEmail())) {
            throw new IllegalArgumentException("Email already exists");
        }

        User user = new User();
        user.setUsername(userRequest.getUsername());
        user.setEmail(userRequest.getEmail());
        user.setPassword(userRequest.getPassword());
        user.setRole(userRequest.getRole());
        User savedUser = userRepository.save(user);
        return new UserResponseDTO(savedUser.getUserId(), savedUser.getUsername(), savedUser.getEmail(), savedUser.getRole(), savedUser.getCreatedAt());


    }

    public UserResponseDTO getUserById(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        return new UserResponseDTO(user.getUserId(), user.getUsername(), user.getEmail(), user.getRole(), user.getCreatedAt());
    }

}
