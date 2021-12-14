package com.gogoaren.indarra.serviceweatherapi.fetch.openweather;


import lombok.AllArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

@EnableConfigurationProperties(OpenWeatherProperties.class)
@Configuration
@AllArgsConstructor
public class FetchWeatherConfiguration {

    private OpenWeatherProperties openWeatherProperties;

    @Bean
    OpenWeatherFetcher weatherFetcher() {
        WebClient client = WebClient.builder()
                .baseUrl(openWeatherProperties.getUrl())
                .defaultCookie("cookieKey", "cookieValue")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
        return new OpenWeatherFetcher(client, openWeatherProperties.getKey());
    }

    @Bean
    OpenWeatherResponseConverter openWeatherResponseConverter() {
        return new OpenWeatherResponseConverter();
    }
}
