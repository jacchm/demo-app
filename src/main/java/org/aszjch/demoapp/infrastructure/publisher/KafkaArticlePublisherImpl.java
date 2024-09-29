package org.aszjch.demoapp.infrastructure.publisher;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aszjch.demoapp.domain.article.port.ArticlePublisher;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
class KafkaArticlePublisherImpl implements ArticlePublisher {

    private final KafkaTemplate<String, String> kafkaTemplate;

    @Override
    public void publish(String message) {
        log.debug("Publishing message: [{}]", message);
        kafkaTemplate.send("articles", message);
    }
}
