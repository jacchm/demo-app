package org.aszjch.demoapp.domain.article.port;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
class KafkaTestListener {

    @KafkaListener(topics = {"articles-created"})
    void listener(String message) {
        log.info("Received message: {}", message);
    }
}
