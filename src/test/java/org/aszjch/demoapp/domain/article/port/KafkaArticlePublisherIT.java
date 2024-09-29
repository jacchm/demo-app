package org.aszjch.demoapp.domain.article.port;

import org.aszjch.demoapp.TestcontainersConfiguration;
import org.awaitility.Awaitility;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.context.annotation.Import;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

@SpringBootTest
@Import(TestcontainersConfiguration.class)
class KafkaArticlePublisherIT {

    @Autowired
    private ArticlePublisher kafkaArticlePublisher;
    @SpyBean
    private KafkaTestListener kafkaTestListener;
    @Captor
    private ArgumentCaptor<String> messageCaptor;

    @Test
    void publishes_messages_correctly() {
        String message = "This is a test message";

        kafkaArticlePublisher.publish(message);

        Awaitility.await()
                .untilAsserted(() -> verify(kafkaTestListener).listener(messageCaptor.capture()));
        assertEquals(message, messageCaptor.getValue());
    }

}