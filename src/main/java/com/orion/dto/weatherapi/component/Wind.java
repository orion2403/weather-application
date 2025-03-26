package com.orion.dto.weatherapi.component;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Wind {

    private Double speed;
    private Integer deg;
    private Double gust;
}
