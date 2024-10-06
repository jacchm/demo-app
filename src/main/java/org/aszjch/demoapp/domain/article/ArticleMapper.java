package org.aszjch.demoapp.domain.article;

import org.aszjch.demoapp.aplication.MapStructConfig;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(config = MapStructConfig.class)
public interface ArticleMapper {

    @Mapping(target = "id", ignore = true)
    Article upsert(@MappingTarget Article entity, Article article);
}
