package com.orion.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record UserLoginDto(

        @Email(message = "The email address does not match.")
        @NotBlank(message = "Login cannot be empty")
        String login,

        @Size(min = 8, message = "Password must be at least 8 characters long.")
        @Pattern(regexp = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{8,20})",
                message = "Password must include at least one digit, one lowercase letter, one uppercase letter, and one special character from: @#$%.")
        String password) {
}