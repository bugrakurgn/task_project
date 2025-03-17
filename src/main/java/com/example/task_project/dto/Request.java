package com.example.task_project.dto;

public record Request(
        String type,
        String query,
        String language,
        String unit
) {
}
