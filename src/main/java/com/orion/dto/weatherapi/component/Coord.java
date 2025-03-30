package com.orion.dto.weatherapi.component;

import java.math.BigDecimal;

public record Coord(

        BigDecimal lon,
        BigDecimal lat
) {
}
