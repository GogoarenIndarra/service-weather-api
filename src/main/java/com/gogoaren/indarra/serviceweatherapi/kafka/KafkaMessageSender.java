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

        StringBuilder message = new StringBuilder()
                .append("Weather msg send: ")
                .append(weather)
                .append(" topic name: ")
                .append(topicName);

        log.info(message.toString());

        ListenableFuture<SendResult<String, Weather>> future =
                kafkaTemplate.send(topicName, weather);

        future.addCallback(new ListenableFutureCallback<>() {

            @Override
            public void onSuccess(SendResult<String, Weather> result) {
                StringBuilder message = new StringBuilder()
                        .append("Sent message=[")
                        .append(weather)
                        .append("] with offset=[")
                        .append(result.getRecordMetadata().offset())
                        .append("]");

                log.info(message.toString());
            }

            @Override
            public void onFailure(Throwable ex) {
                StringBuilder message = new StringBuilder()
                        .append("Unable to send message=[")
                        .append(weather)
                        .append("] due to : ")
                        .append(ex.getMessage());

                log.info(message.toString());
            }
        });
    }

}
