package com.ekos.koopper.modules.user.dto;

import java.util.UUID;

import com.ekos.koopper.modules.user.Role;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UserRequestDTO(
    @NotBlank
    String username,
    @NotBlank
    @Email
    String email,
    @NotBlank
    String password,
    @NotNull
    Role role,
    @NotNull
    UUID departmentId
) {
    
}
