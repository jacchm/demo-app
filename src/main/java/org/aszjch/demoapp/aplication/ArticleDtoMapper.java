package org.aszjch.demoapp.aplication;

import org.aszjch.demoapp.domain.Article;
import org.mapstruct.Mapper;

import static org.mapstruct.InjectionStrategy.CONSTRUCTOR;

@Mapper(componentModel = "spring", injectionStrategy = CONSTRUCTOR)
interface ArticleDtoMapper {
  ArticleDto toDto(Article article);

  Article toEntity(ArticleDto articleDto);

}
