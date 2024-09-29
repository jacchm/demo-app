package org.aszjch.demoapp.aplication;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aszjch.demoapp.domain.article.Article;
import org.aszjch.demoapp.domain.article.ArticleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.aszjch.demoapp.aplication.IdDTO.of;
import static org.springframework.http.HttpStatus.CREATED;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/articles")
class ArticleController {

    private final ArticleService service;
    private final ArticleDtoMapper mapper;

    @GetMapping
    ResponseEntity<List<ArticleDto>> getArticles() {
        log.info("Getting articles");
        List<ArticleDto> articles = service.get()
                .stream()
                .map(mapper::toDto)
                .toList();

        return ResponseEntity.ok(articles);
    }

    @PostMapping
    ResponseEntity<IdDTO> createArticle(@RequestBody ArticleDto articleDto) {
        log.info("Creating article");
        Article article = mapper.toEntity(articleDto);
        Article saved = service.create(article);

        return ResponseEntity
                .status(CREATED)
                .body(of(saved.getId()));
    }

    @PutMapping
    ResponseEntity<IdDTO> updateArticle(@RequestParam Long id, @RequestBody ArticleDto articleDto) {
        log.info("Updating article");
        Article article = mapper.toEntity(articleDto);
        Article updated = service.update(id, article);

        return ResponseEntity
                .ok()
                .body(of(updated.getId()));
    }

    @DeleteMapping
    ResponseEntity<Void> deleteArticle(@RequestParam Long id) {
        log.info("Deleting article");
        service.delete(id);

        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }

}
