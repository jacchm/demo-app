package org.aszjch.demoapp.aplication;

import org.aszjch.demoapp.domain.articlefile.ArticleFile;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.web.multipart.MultipartFile;

import static org.mapstruct.InjectionStrategy.CONSTRUCTOR;

@Mapper(componentModel = "spring", injectionStrategy = CONSTRUCTOR, uses = MapperHelper.class)
interface ArticleFileDtoMapper {

    @Mapping(target = "file", source = "uploadedFile", qualifiedByName = {"MapperHelper", "multipartFileToFile"})
    @Mapping(target = "filename", source = "uploadedFile", qualifiedByName = "getFileName")
    ArticleFile toEntity(Long articleId, MultipartFile uploadedFile);

    @Named("getFileName")
    default String getFileName(MultipartFile multipartFile) {
        return multipartFile.getOriginalFilename();
    }
}
