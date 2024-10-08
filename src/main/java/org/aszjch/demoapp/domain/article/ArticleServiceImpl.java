package org.aszjch.demoapp.domain.article;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aszjch.demoapp.domain.article.port.ArticlePublisher;
import org.aszjch.demoapp.domain.article.port.ArticleRepository;
import org.aszjch.demoapp.domain.articlefile.ArticleFile;
import org.aszjch.demoapp.domain.articlefile.port.ArticleFileAssignmentService;

import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
class ArticleServiceImpl implements ArticleService, ArticleFileAssignmentService {

    private final ArticleRepository repository;
    private final ArticlePublisher publisher;
    private final ArticleMapper mapper;

    @Override
    public List<Article> get() {
        return repository.findAll();
    }

    @Override
    public Article create(final Article article) {
        final Article saved = repository.save(article);
        publisher.publish(new ArticleCreatedDto(saved.getId(), saved.getTitle(), saved.getAuthor(),
                                                saved.getAbstractText()));
        return saved;
    }

    @Override
    public Optional<Article> getById(final Long id) {
        return repository.findById(id);
    }

    @Override
    public Article update(final Long id, final Article article) {
        Article entity = getById(id).orElse(new Article().withId(id));
        entity = mapper.upsert(entity, article);
        return repository.save(entity);
    }

    @Override
    public void delete(final Long id) {
        repository.deleteById(id);
    }

    @Override
    public void assignFile(final ArticleFile articleFile) {
        getById(articleFile.getArticleId()).ifPresentOrElse(article -> {
            article.setFilename(articleFile.getFilename());
            repository.save(article);
            log.info("File [{}] has been successfully assigned to article [{}].", articleFile.getFilename(),
                     article.getId());
        }, () -> log.error("File assignment failed. Article id {} has not been found", articleFile.getArticleId()));
    }

    @Override
    public Optional<String> unassignFile(final Long id) {
        final Optional<String> filename;
        final Optional<Article> articleOpt = getById(id);
        filename = articleOpt.map(Article::getFilename);
        articleOpt.ifPresentOrElse(this::removeFilenameFromEntity,
                                   () -> log.error("Article id {} has not been found", id));
        return filename;
    }

    private void removeFilenameFromEntity(final Article article) {
        article.setFilename(null);
        repository.save(article);
    }

}
