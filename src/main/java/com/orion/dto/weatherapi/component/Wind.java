package com.orion.dto.weatherapi.component;

import java.math.BigDecimal;

public record Wind(

        BigDecimal speed,
        Integer deg,
        BigDecimal gust
) {
}
