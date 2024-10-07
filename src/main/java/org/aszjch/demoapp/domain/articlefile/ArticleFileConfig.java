package org.aszjch.demoapp.domain.articlefile;

import org.aszjch.demoapp.domain.article.ArticleService;
import org.aszjch.demoapp.domain.articlefile.port.ArticleFileAssignmentService;
import org.aszjch.demoapp.domain.articlefile.port.ArticleFileStorageService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class ArticleFileConfig {

    @Bean
    ArticleFileAssignmentService articleFileAssignmentService(final ArticleService articleService) {
        return (ArticleFileAssignmentService) articleService;
    }

    @Bean
    ArticleFileService articleFileService(final ArticleFileStorageService articleFileStorageService,
                                          final ArticleFileAssignmentService articleFileAssignmentService,
                                          final ArticleFileMetadataMapper articleFileMetadataMapper) {
        return new ArticleFileServiceImpl(articleFileStorageService, articleFileAssignmentService,
                                          articleFileMetadataMapper);
    }

}
