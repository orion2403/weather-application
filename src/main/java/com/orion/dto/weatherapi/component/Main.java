package com.orion.dto.weatherapi.component;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Main {

    private Double temp;
    @JsonProperty("feels_like")
    private Double feelsLike;
    @JsonProperty("temp_min")
    private Double tempMin;
    @JsonProperty("temp_max")
    private Double tempMax;
    private Integer pressure;
    private Integer humidity;
    @JsonProperty("sea_level")
    private Integer seaLevel;
    @JsonProperty("grnd_level")
    private Integer grndLevel;
}
