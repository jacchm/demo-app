package org.aszjch.demoapp.aplication;

import org.aszjch.demoapp.domain.articlefile.ArticleFile;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.web.multipart.MultipartFile;

@Mapper(config = MapStructConfig.class)
interface ArticleFileMapper {

    @Mapping(target = "file", source = "uploadedFile", qualifiedByName = {"MapperHelper", "multipartFileToFile"})
    @Mapping(target = "filename", source = "uploadedFile", qualifiedByName = "getFileName")
    ArticleFile toEntity(final Long articleId, final MultipartFile uploadedFile);

    @Named("getFileName")
    default String getFileName(final MultipartFile multipartFile) {
        return multipartFile.getOriginalFilename();
    }
}
