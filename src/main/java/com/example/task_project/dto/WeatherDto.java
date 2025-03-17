package com.example.task_project.dto;

import com.example.task_project.model.WeatherEntity;

public record WeatherDto(
        String cityName,
        String country,
        Integer temperature
) {
    public static WeatherDto convert(WeatherEntity weatherEntity) {
        return new WeatherDto(weatherEntity.getCityName(), weatherEntity.getCountry(), weatherEntity.getTemperature());
    }


}
