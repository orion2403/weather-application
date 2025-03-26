package com.orion.dto.weatherapi;

import com.orion.dto.weatherapi.component.Coord;
import com.orion.dto.weatherapi.component.Weather;
import com.orion.dto.weatherapi.component.Main;
import com.orion.dto.weatherapi.component.Wind;
import com.orion.dto.weatherapi.component.Clouds;
import com.orion.dto.weatherapi.component.Sys;

import java.util.List;

public record WeatherApiDto(

        Coord coord,
        List<Weather> weather,
        String base,
        Main main,
        Long visibility,
        Wind wind,
        Clouds clouds,
        Long dt,
        Sys sys,
        Long timezone,
        Long id,
        String name,
        Integer cod
) {
}
