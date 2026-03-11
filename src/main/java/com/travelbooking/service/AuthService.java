package com.travelbooking.service;

import org.springframework.stereotype.Service;
import com.travelbooking.repository.UserRepository;
import com.travelbooking.dto.request.LoginRequestDTO;
import com.travelbooking.dto.request.RegisterRequestDTO;
import com.travelbooking.dto.response.UserResponseDTO;
import com.travelbooking.entity.Role;
import com.travelbooking.entity.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.authentication.AuthenticationManager;
import com.travelbooking.security.JwtUtil;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import com.travelbooking.security.CustomUserDetails;

@Service
public class AuthService {

    private UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    public AuthService(AuthenticationManager authenticationManager, JwtUtil jwtUtil, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void register(RegisterRequestDTO userRequest)
    {
        if(userRepository.existsByEmail(userRequest.getEmail())) {
            throw new IllegalArgumentException("Email already exists");
        }

        User user = new User();
        user.setUsername(userRequest.getUsername());
        user.setEmail(userRequest.getEmail());
        user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        user.setRole(Role.USER);
        userRepository.save(user);
    }

    public UserResponseDTO getUserById(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        return new UserResponseDTO(user.getUserId(), user.getUsername(), user.getEmail(), user.getRole(), user.getCreatedAt());
    }
    public String login(LoginRequestDTO request)
    {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        return jwtUtil.generateToken(userDetails.getUsername());
    }

}
