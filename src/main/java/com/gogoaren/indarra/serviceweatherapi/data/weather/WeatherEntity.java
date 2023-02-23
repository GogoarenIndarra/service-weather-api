package com.gogoaren.indarra.serviceweatherapi.data.weather;

import com.gogoaren.indarra.serviceweatherapi.fetch.Weather;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Builder
@Table(name = "weather")
@Entity
@AllArgsConstructor
public class WeatherEntity implements Serializable {

    private static final long serialVersionUID = 345L;

    @Id
    private UUID uuid;
    private Instant created;
    private String city;
    private BigDecimal temperature;
    private double humidity;
    private double wind;
    private String country;
    private String countryCode;

    public WeatherEntity(final Weather weather) {
        this.uuid = UUID.randomUUID();
        this.created = Instant.now();
        this.city = weather.getCity();
        this.temperature = weather.getTemperature();
        this.humidity = weather.getHumidity();
        this.wind = weather.getWind();
        this.country = weather.getCountry();
        this.countryCode = weather.getCountryCode();
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof final WeatherEntity weatherEntity)) {
            return false;
        }
        return Objects.equals(uuid, weatherEntity.uuid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid);
    }
}
