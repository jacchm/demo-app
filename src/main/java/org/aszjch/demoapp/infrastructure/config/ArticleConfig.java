package org.aszjch.demoapp.infrastructure.config;

import org.aszjch.demoapp.domain.article.ArticleService;
import org.aszjch.demoapp.domain.article.ArticleServiceImpl;
import org.aszjch.demoapp.domain.article.port.ArticlePublisher;
import org.aszjch.demoapp.domain.article.port.ArticleRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ArticleConfig {

    @Bean
    public ArticleService articleService(ArticleRepository articleRepository,
                                         ArticlePublisher articlePublisher) {
        return new ArticleServiceImpl(articleRepository, articlePublisher);
    }

}
