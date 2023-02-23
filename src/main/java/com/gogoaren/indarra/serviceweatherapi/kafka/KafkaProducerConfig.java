package com.gogoaren.indarra.serviceweatherapi.kafka;

import com.gogoaren.indarra.serviceweatherapi.fetch.Weather;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaProducerConfig {

    @Value(value = "${kafka.bootstrap.Address}")
    private String bootstrapAddress;
    @Value(value = "${weather.topic.name}")
    private String weatherTopic;

    @Bean
    public NewTopic weatherTopic() {
        return new NewTopic(weatherTopic, 1, (short) 1);
    }

    @Bean
    public ProducerFactory<String, Weather> weatherProducerFactory() {
        final Map<String, Object> configProps = new HashMap<>();
        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        configProps.put(JsonSerializer.ADD_TYPE_INFO_HEADERS, false);
        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);

        return new DefaultKafkaProducerFactory<>(configProps);
    }

    @Bean
    public KafkaTemplate<String, Weather> weatherKafkaTemplate() {
        return new KafkaTemplate<>(weatherProducerFactory());
    }

    @Bean
    public KafkaMessageSender kafkaMessageSender() {
        return new KafkaMessageSender(weatherTopic, weatherKafkaTemplate());
    }
}