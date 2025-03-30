package com.orion.dto.response;

import java.time.LocalDateTime;
import java.util.UUID;

public record SessionResponseDto (
        UUID id,
        LocalDateTime expiresAt,
        Long userId) {
}
