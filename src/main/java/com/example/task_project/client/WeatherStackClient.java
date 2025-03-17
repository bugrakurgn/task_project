package com.example.task_project.client;


import com.example.task_project.dto.WeatherResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@FeignClient(
        name = "weatherstackClient",
        url = "http://api.weatherstack.com"  // NOTE: Free WeatherStack plan requires HTTP, not HTTPS
)
public interface WeatherStackClient {

    @GetMapping("/current")
    WeatherResponse getCurrentWeather(
            @RequestParam("access_key") String accessKey,
            @RequestParam("query") String city
    );
}
