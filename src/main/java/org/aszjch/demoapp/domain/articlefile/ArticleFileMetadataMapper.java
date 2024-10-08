package org.aszjch.demoapp.domain.articlefile;

import org.aszjch.demoapp.aplication.MapStructConfig;
import org.mapstruct.Mapper;

@Mapper(config = MapStructConfig.class)
interface ArticleFileMetadataMapper {

    ArticleFileMetadata toMetadata(Long articleId, String filename);
}
