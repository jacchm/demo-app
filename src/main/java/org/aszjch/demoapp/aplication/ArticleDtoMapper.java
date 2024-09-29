package org.aszjch.demoapp.aplication;

import org.aszjch.demoapp.domain.article.Article;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import static org.mapstruct.InjectionStrategy.CONSTRUCTOR;

@Mapper(componentModel = "spring", injectionStrategy = CONSTRUCTOR, uses = MapperHelper.class)
interface ArticleDtoMapper {

    ArticleDto toDto(Article article);

    @Mapping(target = "creationDate", source = ".", qualifiedByName = {"MapperHelper", "getCurrentTime"})
    @Mapping(ignore = true, target = "id")
    @Mapping(ignore = true, target = "filename")
    Article toEntity(ArticleDto articleDto);

}
