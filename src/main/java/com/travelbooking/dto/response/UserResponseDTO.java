package com.travelbooking.dto.response;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.travelbooking.entity.*;




public class UserResponseDTO {

    private Long UserId;
    private String username;
    private String email;
    private Role role;
    private LocalDateTime createdAt;

    public UserResponseDTO(Long UserId, String username, String email, Role role, LocalDateTime createdAt) {
        this.UserId = UserId;
        this.username = username;
        this.email = email;
        this.role = role;
        this.createdAt = createdAt;
    }

    public Long getUserId() {
        return UserId;
    }
    public String getUsername() {
        return username;
    }
    public String getEmail() {
        return email;
    }
    public Role getRole() {
        return role;
    }
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

}
