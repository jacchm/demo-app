package org.aszjch.demoapp.infrastructure.config;

import org.aszjch.demoapp.domain.ArticleService;
import org.aszjch.demoapp.domain.ArticleServiceImpl;
import org.aszjch.demoapp.domain.port.ArticleRepository;
import org.aszjch.demoapp.domain.port.ArticlePublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ArticleConfig {
  @Bean
  public ArticleService articleService(final ArticleRepository articleRepository,
                                       final ArticlePublisher articlePublisher) {
    return new ArticleServiceImpl(articleRepository, articlePublisher);
  }

}
