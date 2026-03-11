package com.travelbooking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.travelbooking.service.AuthService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.travelbooking.dto.request.RegisterRequestDTO;
import com.travelbooking.dto.response.UserResponseDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.travelbooking.dto.request.LoginRequestDTO;


@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService userService;

    @PostMapping("/register")
    public ResponseEntity<String> createUser(@RequestBody RegisterRequestDTO userRequest) {
         userService.register(userRequest);
        return ResponseEntity.ok("User registered successfully");
    }

    @GetMapping("/{email}")
    public ResponseEntity<UserResponseDTO> getUserByEmail(@PathVariable String email) {
        UserResponseDTO response = userService.getUserById(email);
        return ResponseEntity.ok(response);
    }
    @PostMapping("login")
    public ResponseEntity<String> login(@RequestBody LoginRequestDTO request) {
        String token = userService.login(request);
        return ResponseEntity.ok(token);
    }
    


    
    

}
