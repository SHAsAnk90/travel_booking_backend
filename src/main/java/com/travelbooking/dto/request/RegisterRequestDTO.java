package com.travelbooking.dto.request;
import com.travelbooking.entity.*;

import jakarta.validation.constraints.*;



public class RegisterRequestDTO {

    @NotBlank(message = "Name is required")
    private String username;

    @Email(message = "Invalid email format")
    @NotBlank(message = "Email is required")
    private String email;

    @Size(min = 6, message = "Password must be at least 6 characters long")
    @NotBlank(message = "Password is required")
    private String password;


    public String getUsername() {
        return username;
    }
    public String getEmail() {
        return email;
    }
    public String getPassword() {
        return password;
    }

}
