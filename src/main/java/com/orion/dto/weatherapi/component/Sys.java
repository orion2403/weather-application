package com.orion.dto.weatherapi.component;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Sys {

    private Integer type;
    private Long id;
    private String country;
    private Long sunrise;
    private Long sunset;
}
