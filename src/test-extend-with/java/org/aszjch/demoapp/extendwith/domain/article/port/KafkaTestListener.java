package org.aszjch.demoapp.extendwith.domain.article.port;

import lombok.extern.slf4j.Slf4j;
import org.aszjch.demoapp.domain.article.ArticleCreatedDto;
import org.springframework.context.annotation.Profile;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@Profile("test-extend-with")
class KafkaTestListener {

    @KafkaListener(topics = {"articles-created"})
    void listener(final ArticleCreatedDto message) {
        log.info("Received message: {}", message);
    }
}
