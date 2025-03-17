package com.example.task_project.constants;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Constants {

    public static String API_URL;
    public static String API_KEY;
    public static String ACCESS_KEY_PARAM = "?access_key=";
    public static String QUERY_KEY_PARAM = "&query=";
    public static String WEATHER_CACHE_NAME;
    public static Integer API_CALL_LIMIT;


    @Value("${weather-stack.api-url}")
    public void setApiUrl(String apiUrl) {
        Constants.API_URL = apiUrl;
    }

    @Value("${weather-stack.api-key}")
    public void setApiKey(String apiKey) {
        Constants.API_KEY = apiKey;
    }

    @Value("${weather-stack.cache-name}")
    public void setWeatherCacheName(String cacheName) {
        Constants.WEATHER_CACHE_NAME = cacheName;
    }

    @Value("${weather-stack.api-call-limit}")
    public void setApiCallLimit(Integer apiCallLimit) {
        API_CALL_LIMIT = apiCallLimit;
    }


}
