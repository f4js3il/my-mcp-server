package com.example.mymcpserver.service;

import com.example.mymcpserver.payload.Forecast;
import com.example.mymcpserver.payload.Points;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.stream.Collectors;

@Service
public class WeatherService {

    private static final String BASE_URL = "https://api.weather.gov";

    private final RestClient restClient;

    public WeatherService() {

        this.restClient = RestClient.builder()
                .baseUrl(BASE_URL)
                .defaultHeader("Accept", "application/geo+json")
                .defaultHeader("User-Agent", "WeatherApiClient/1.0 (your@email.com)")
                .build();
    }

    @Tool(name="Get Weather forecast",description = "Get weather forecast for a specific latitude/longitude")
    public String getWeatherForecastByLocation(double latitude, double longitude) {

        Points points = restClient.get()
                .uri("/points/{latitude},{longitude}", latitude, longitude)
                .retrieve()
                .body(Points.class);

        Forecast forecast = restClient.get().uri(points.properties().forecast()).retrieve().body(Forecast.class);

        String forecastText = forecast.properties().periods().stream().map(p -> {
            return String.format("""
					%s:
					Temperature: %s %s
					Wind: %s %s
					Forecast: %s
					""", p.name(), p.temperature(), p.temperatureUnit(), p.windSpeed(), p.windDirection(),
                    p.detailedForecast());
        }).collect(Collectors.joining());

        return forecastText;
    }
}
