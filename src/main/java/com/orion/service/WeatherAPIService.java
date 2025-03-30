package com.orion.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.orion.dto.weatherapi.LocationApiDto;
import com.orion.dto.weatherapi.WeatherApiDto;
import com.orion.exception.ClientSendingException;
import com.orion.exception.InvalidURISyntaxException;
import com.orion.exception.JsonConversionException;
import org.apache.http.client.utils.URIBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.util.Arrays;
import java.util.List;

import static java.net.http.HttpResponse.BodyHandlers;

@Service
public class WeatherAPIService {

    public static final String MEASUREMENT_CELSIUS = "metric";

    @Value("${api_key}")
    private String apiKey;

    private static final String FIND_LOCATIONS_BY_NAME = "http://api.openweathermap.org/geo/1.0/direct";
    private static final String FIND_WEATHER_BY_COORDINATES = "https://api.openweathermap.org/data/2.5/weather";

    private final ObjectMapper mapper = new ObjectMapper();

    public List<LocationApiDto> findLocationByName(String name) {
        try {
            var uri = new URIBuilder(FIND_LOCATIONS_BY_NAME)
                    .addParameter("q", name)
                    .addParameter("appid", apiKey)
                    .addParameter("limit", "5")
                    .build();
            var client = HttpClient.newHttpClient();
            var request = HttpRequest.newBuilder(uri)
                    .GET()
                    .build();
            var responseBody = client
                    .send(request, BodyHandlers.ofString())
                    .body();
            return Arrays.asList(mapper.readValue(responseBody, LocationApiDto[].class));
        } catch (URISyntaxException e) {
            throw new InvalidURISyntaxException("URI syntax is invalid");
        } catch (JsonProcessingException e) {
            throw new JsonConversionException("Conversion to JSON failed");
        } catch (InterruptedException | IOException e) {
            throw new ClientSendingException("Request from client was not executed");
        }
    }

    public List<WeatherApiDto> findWeatherByLocation(LocationApiDto locationApiDto) {
        try {
            var uri = new URIBuilder(FIND_WEATHER_BY_COORDINATES)
                    .addParameter("lat", locationApiDto.latitude().toString())
                    .addParameter("lon", locationApiDto.longitude().toString())
                    .addParameter("appid", apiKey)
                    .addParameter("units", MEASUREMENT_CELSIUS)
                    .build();

            var client = HttpClient.newHttpClient();
            var request = HttpRequest.newBuilder(uri)
                    .GET()
                    .build();
            var responseBody = client
                    .send(request, BodyHandlers.ofString())
                    .body();
            return Arrays.asList(mapper.readValue(responseBody, WeatherApiDto[].class));
        } catch (URISyntaxException e) {
            throw new InvalidURISyntaxException("URI syntax is invalid");
        } catch (JsonProcessingException e) {
            throw new JsonConversionException("Conversion to JSON failed");
        } catch (InterruptedException | IOException e) {
            throw new ClientSendingException("Request from client was not executed");
        }
    }
}

