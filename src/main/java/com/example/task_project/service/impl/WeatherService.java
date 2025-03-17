package com.example.task_project.service.impl;

import com.example.task_project.client.WeatherStackClient;
import com.example.task_project.constants.Constants;
import com.example.task_project.dto.WeatherDto;
import com.example.task_project.dto.WeatherResponse;
import com.example.task_project.model.WeatherEntity;
import com.example.task_project.repository.WeatherRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Service
public class WeatherService {
    private final WeatherRepository weatherRepository;
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final WeatherStackClient weatherClient;


    private static final String API_URL = "http://api.weatherstack.com/current?access_key=&query=";


    public WeatherService(WeatherRepository weatherRepository, RestTemplate restTemplate, WeatherStackClient weatherClient) {
        this.weatherRepository = weatherRepository;
        this.restTemplate = restTemplate;
        this.weatherClient = weatherClient;
    }

    @Cacheable(value = "weatherCache", key = "#city")
    public WeatherDto getWeatherByCityName(String city) {
        Optional<WeatherEntity> weatherEntityOptional = weatherRepository.findFirstByRequestedCityNameOrderByUpdateTimeDesc(city);

        return weatherEntityOptional.map(weatherEntity -> {
            if (weatherEntity.getUpdateTime().isAfter(LocalDateTime.now().minusSeconds(30))) {
                return WeatherDto.convert(getWeatherFromWeatherStack(city));
            }
            return WeatherDto.convert(weatherEntity);
        }).orElseGet(() -> WeatherDto.convert(getWeatherFromWeatherStack(city)));
    }

    private WeatherEntity getWeatherFromWeatherStack(String city) {
        //ResponseEntity<String> responseEntity = restTemplate.getForEntity(getWeatherStackUrl(city), String.class);
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(API_URL + city, String.class);

        try {
            WeatherResponse weatherResponse = objectMapper.readValue(responseEntity.getBody(), WeatherResponse.class);
            return saveWeatherEntity(city, weatherResponse);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private String getWeatherStackUrl(String city) {
        return Constants.API_URL + Constants.ACCESS_KEY_PARAM + Constants.API_KEY + Constants.QUERY_KEY_PARAM + city;
    }

    private WeatherEntity saveWeatherEntity(String city, WeatherResponse weatherResponse) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        WeatherEntity weatherEntity = new WeatherEntity(city,
                weatherResponse.location().name(),
                weatherResponse.location().country(),
                weatherResponse.current().temperature(),
                LocalDateTime.now(),
                LocalDateTime.parse(weatherResponse.location().localtime(),
                dateTimeFormatter));
        return weatherRepository.save(weatherEntity);
    }
}
