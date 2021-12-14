package com.gogoaren.indarra.serviceweatherapi.fetch.openweather;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ConfigurationProperties(prefix = "open.weather")
public class OpenWeatherProperties {

    private String key;
    private String url;

}
