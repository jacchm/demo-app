package org.aszjch.demoapp.aplication;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aszjch.demoapp.domain.articlefile.ArticleFile;
import org.aszjch.demoapp.domain.articlefile.ArticleFileService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.CREATED;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/articles/files")
class ArticleFileController {

    private final ArticleFileService service;
    private final ArticleFileDtoMapper mapper;

    @PostMapping
    ResponseEntity<String> uploadFile(@RequestBody ArticleFileDto articleFileDto) {
        log.info("Creating articleFile");
        ArticleFile articleFile = mapper.toEntity(articleFileDto);
        service.upload(articleFile);

        return ResponseEntity
                .status(CREATED)
                .body(articleFileDto.getFilename());
    }

}
