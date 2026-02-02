package com.ekos.koopper.modules.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UserRequestEdtiableDTO(
    @NotBlank
    String username,
    
    @Email
    String email,
    String password
) {
}
