package com.orion.dto.weatherapi.component;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public record Main(

        BigDecimal temp,
        @JsonProperty("feels_like")

        BigDecimal feelsLike,

        @JsonProperty("temp_min")
        BigDecimal tempMin,

        @JsonProperty("temp_max")
        BigDecimal tempMax,

        Integer pressure,

        Integer humidity,

        @JsonProperty("sea_level")
        Integer seaLevel,

        @JsonProperty("grnd_level")
        Integer grndLevel
) {
}
