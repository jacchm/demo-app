package org.aszjch.demoapp.infrastructure.config;

import org.aszjch.demoapp.domain.article.ArticleService;
import org.aszjch.demoapp.domain.articlefile.ArticleFileService;
import org.aszjch.demoapp.domain.articlefile.ArticleFileServiceImpl;
import org.aszjch.demoapp.domain.articlefile.port.ArticleFileAssignmentService;
import org.aszjch.demoapp.domain.articlefile.port.ArticleFileStorageService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ArticleFileConfig {

    @Bean
    public ArticleFileAssignmentService articleFileAssignmentService(final ArticleService articleService) {
        return (ArticleFileAssignmentService) articleService;
    }

    @Bean
    public ArticleFileService articleFileService(final ArticleFileStorageService articleFileStorageService,
                                                 final ArticleFileAssignmentService articleFileAssignmentService) {
        return new ArticleFileServiceImpl(articleFileStorageService, articleFileAssignmentService);
    }

}
