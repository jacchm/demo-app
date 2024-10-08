package org.aszjch.demoapp.aplication;

import org.aszjch.demoapp.domain.article.Article;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapStructConfig.class)
interface ArticleDtoMapper {

    ArticleDto toDto(final Article article);

    @Mapping(target = "creationDate", source = ".", qualifiedByName = {"CurrentTimeProvider", "getCurrentTime"})
    @Mapping(ignore = true, target = "id")
    @Mapping(ignore = true, target = "filename")
    Article toEntity(final ArticleDto articleDto);

}
