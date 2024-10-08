package org.aszjch.demoapp.extendwith.domain.article.port;

import org.aszjch.demoapp.DemoAppApplication;
import org.aszjch.demoapp.domain.article.ArticleCreatedDto;
import org.aszjch.demoapp.domain.article.port.ArticlePublisher;
import org.aszjch.demoapp.extendwith.tcconfig.KafkaContainerExtension;
import org.aszjch.demoapp.extendwith.tcconfig.MinIOContainerExtension;
import org.aszjch.demoapp.extendwith.tcconfig.PostgresContainerExtension;
import org.awaitility.Awaitility;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

@SpringBootTest(classes = DemoAppApplication.class)
@ExtendWith({MinIOContainerExtension.class, PostgresContainerExtension.class, KafkaContainerExtension.class})
@ActiveProfiles("test-extend-with")
class KafkaArticlePublisherIT {

    @Autowired
    private ArticlePublisher kafkaArticlePublisher;
    @SpyBean
    private KafkaTestListener kafkaTestListener;
    @Captor
    private ArgumentCaptor<ArticleCreatedDto> messageCaptor;

    @Test
    void publishesMessagesCorrectly() {
        final ArticleCreatedDto articleCreatedDto = new ArticleCreatedDto(1L, "Test article title", "Chuck Testa",
                                                                          "Some test article abstract");

        kafkaArticlePublisher.publish(articleCreatedDto);

        Awaitility.await()
                .untilAsserted(() -> verify(kafkaTestListener).listener(messageCaptor.capture()));
        assertEquals(articleCreatedDto, messageCaptor.getValue());
    }

}