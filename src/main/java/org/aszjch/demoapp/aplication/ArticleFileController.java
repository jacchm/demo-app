package org.aszjch.demoapp.aplication;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aszjch.demoapp.domain.articlefile.ArticleFile;
import org.aszjch.demoapp.domain.articlefile.ArticleFileService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import static org.springframework.http.HttpStatus.CREATED;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/articles/files")
class ArticleFileController {

    private final ArticleFileService service;
    private final ArticleFileMapper mapper;

    @PostMapping
    ResponseEntity<String> uploadFile(@RequestParam final Long articleId,
                                      @RequestPart final MultipartFile uploadedFile) {
        log.info("Creating articleFile");
        final ArticleFile articleFile = mapper.toEntity(articleId, uploadedFile);
        service.upload(articleFile);

        return ResponseEntity
                .status(CREATED)
                .body(uploadedFile.getName());
    }

}
