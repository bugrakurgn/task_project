package com.example.task_project.dto;

public record WeatherResponse(
        Request request,
        Location location,
        Current current
) {
}
