package com.orion.dto.response;

import java.math.BigDecimal;

public record LocationResponseDto(
        String name,
        BigDecimal temperature
) {
}
