package com.orion.dto.weatherapi.component;

public record Weather(

        Long id,
        String main,
        String description,
        String icon
) {
}
