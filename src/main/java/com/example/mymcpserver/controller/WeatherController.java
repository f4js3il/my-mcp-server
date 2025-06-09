package com.example.mymcpserver.controller;

import com.example.mymcpserver.service.WeatherService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WeatherController {

    private final WeatherService weatherService;

    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping("/weather")
    public String getWeather(@RequestParam double latitude, @RequestParam double longitude) {
       return weatherService.getWeatherForecastByLocation(latitude,longitude);
    }
}
