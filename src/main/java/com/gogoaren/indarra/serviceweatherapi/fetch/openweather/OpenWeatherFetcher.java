package com.gogoaren.indarra.serviceweatherapi.fetch.openweather;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.Duration;

@RequiredArgsConstructor
public class OpenWeatherFetcher {

    private final WebClient client;
    private final String apiKey;

    public OpenWeatherResponse fetchWeatherByCityName(final String cityName) {

        return client.get()
                .uri(uriBuilder -> uriBuilder.path("/")
                        .queryParam("q", cityName)
                        .queryParam("apiKey", apiKey)
                        .build())
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(OpenWeatherResponse.class)
                .share()
                .block(Duration.ofSeconds(5));
    }

    public OpenWeatherResponse fetchWeatherByCityLocation(final double lat, final double lng) {

        return client.get()
                .uri(uriBuilder -> uriBuilder.path("/")
                        .queryParam("lat", lat)
                        .queryParam("lon", lng)
                        .queryParam("apiKey", apiKey)
                        .build())
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(OpenWeatherResponse.class)
                .block(Duration.ofSeconds(20));
    }
}
