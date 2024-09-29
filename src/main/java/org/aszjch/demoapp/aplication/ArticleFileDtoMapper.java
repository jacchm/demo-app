package org.aszjch.demoapp.aplication;

import org.aszjch.demoapp.domain.articlefile.ArticleFile;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import static org.mapstruct.InjectionStrategy.CONSTRUCTOR;

@Mapper(componentModel = "spring", injectionStrategy = CONSTRUCTOR, uses = MapperHelper.class)
interface ArticleFileDtoMapper {

    @Mapping(target = "file", source = "file", qualifiedByName = {"MapperHelper", "multipartFileToFile"})
    ArticleFile toEntity(ArticleFileDto dto);
}
