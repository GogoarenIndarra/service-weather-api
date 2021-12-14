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

    public void sendMessage(Weather weather) {

        log.info("Weather msg send: " + weather + " topic name: " + topicName);

        ListenableFuture<SendResult<String, Weather>> future =
                kafkaTemplate.send(topicName, weather);

        future.addCallback(new ListenableFutureCallback<>() {

            @Override
            public void onSuccess(SendResult<String, Weather> result) {
                log.info("Sent message=[" + weather +
                        "] with offset=[" + result.getRecordMetadata().offset() + "]");
            }

            @Override
            public void onFailure(Throwable ex) {
                log.error("Unable to send message=["
                        + weather + "] due to : " + ex.getMessage());
            }
        });
    }

}
