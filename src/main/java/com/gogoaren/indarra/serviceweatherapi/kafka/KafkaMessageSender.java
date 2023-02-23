package com.gogoaren.indarra.serviceweatherapi.kafka;

import com.gogoaren.indarra.serviceweatherapi.fetch.Weather;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@AllArgsConstructor
@Slf4j
public class KafkaMessageSender {

    final String topicName;
    private KafkaTemplate<String, Weather> kafkaTemplate;

    public void sendMessage(final Weather weather) {
        log.info("Weather msg send: {}, topic name: {}", weather, topicName);
        final ListenableFuture<SendResult<String, Weather>> future = kafkaTemplate.send(topicName, weather);

        future.addCallback(new ListenableFutureCallback<>() {
            @Override
            public void onSuccess(final SendResult<String, Weather> result) {
                log.info("Sent message=[{}] with offset=[{}]", weather, result.getRecordMetadata().offset());
            }

            @Override
            public void onFailure(final Throwable throwable) {
                log.info("Unable to send message=[{}] due to :{}", weather, throwable.getMessage());
            }
        });
    }
}
