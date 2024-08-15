package org.aszjch.demoapp.domain;

import org.aszjch.demoapp.domain.port.ArticleRepository;
import org.aszjch.demoapp.domain.port.ArticlePublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class ArticleConfig {
  @Bean
  ArticleService articleService(final ArticleRepository articleRepository,
                                final ArticlePublisher articlePublisher) {
    return new ArticleServiceImpl(articleRepository, articlePublisher);
  }

}
