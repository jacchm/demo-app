package org.aszjch.demoapp.domain.article;

import org.aszjch.demoapp.domain.article.port.ArticlePublisher;
import org.aszjch.demoapp.domain.article.port.ArticleRepository;
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
