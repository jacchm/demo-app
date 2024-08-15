package org.aszjch.demoapp.infrastructure.publisher;

import org.aszjch.demoapp.domain.port.ArticlePublisher;
import org.springframework.stereotype.Component;

@Component
class PostgresArticlePublisherImpl implements ArticlePublisher {
  @Override
  public void publish(String message) {
    // todo: implement

  }
}
