package com.orion.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserLoginDto(
        @Email(message = "The email address does not match.") @NotBlank(message = "Login cannot be empty") String login,
        @Size(min = 8, message = "Password must be at least 8 characters long.") String password) {
}
