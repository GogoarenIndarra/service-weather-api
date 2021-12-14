package com.gogoaren.indarra.serviceweatherapi.data.weather;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
interface WeatherEntityRepository extends CrudRepository<WeatherEntity, UUID> {

    @Query(value = "SELECT * from  weather w " +
            "WHERE w.created is not null " +
            "AND w.city= :cityName " +
            "ORDER BY w.created DESC LIMIT 1",
            nativeQuery = true)
    Optional<WeatherEntity> findLatestCityWeather(
            @Param("cityName") String cityName
    );

    @Query(value = "SELECT DISTINCT ON (city) *  FROM weather w " +
            "ORDER BY w.temperature DESC LIMIT :limit",
            nativeQuery = true)
    List<WeatherEntity> findTopWarmestCity(
            @Param("limit") int numberOfRecords
    );

    @Query(value = "SELECT * FROM weather",
            nativeQuery = true)
    List<WeatherEntity> selectAllForStreamPractice();

}
