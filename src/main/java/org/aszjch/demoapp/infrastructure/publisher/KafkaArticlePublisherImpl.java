package org.aszjch.demoapp.infrastructure.publisher;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aszjch.demoapp.domain.article.ArticleCreatedDto;
import org.aszjch.demoapp.domain.article.port.ArticlePublisher;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
class KafkaArticlePublisherImpl implements ArticlePublisher {

    private final KafkaTemplate<String, ArticleCreatedDto> kafkaTemplate;

    @Override
    public void publish(final ArticleCreatedDto articleCreatedDto) {
        log.debug("Publishing message: [{}]", articleCreatedDto);
        kafkaTemplate.send("articles-created", articleCreatedDto);
    }

}
