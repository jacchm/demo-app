package org.aszjch.demoapp.infrastructure.publisher;

import lombok.RequiredArgsConstructor;
import org.aszjch.demoapp.domain.port.ArticlePublisher;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
class KafkaArticlePublisherImpl implements ArticlePublisher {

    private final KafkaTemplate<String, String> kafkaTemplate;

    @Override
    public void publish(String message) {
        kafkaTemplate.send("articles", message);
    }
}
