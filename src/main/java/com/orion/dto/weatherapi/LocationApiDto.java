package com.orion.dto.weatherapi;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

@JsonIgnoreProperties(ignoreUnknown = true)
public record LocationApiDto(

        String name,
        @JsonProperty("lon")
        BigDecimal latitude,
        @JsonProperty("lat")
        BigDecimal longitude,
        String country,
        String state
) {
}
