package com.orion.dto.weatherapi.component;

public record Sys(

        Integer type,
        Long id,
        String country,
        Long sunrise,
        Long sunset
) {
}
