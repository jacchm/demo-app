package org.aszjch.demoapp.infrastructure.repository;

import org.aszjch.demoapp.domain.article.Article;
import org.mapstruct.Mapper;

import java.util.List;

import static org.mapstruct.InjectionStrategy.CONSTRUCTOR;

@Mapper(componentModel = "spring", injectionStrategy = CONSTRUCTOR)
interface ArticleEntityMapper {

    ArticleEntity toEntity(Article article);

    Article toDomain(ArticleEntity articleEntity);

    List<Article> toDomain(List<ArticleEntity> articleEntities);

    List<ArticleEntity> toEntity(List<Article> articles);
}
