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
public class ArticleServiceImpl implements ArticleService, ArticleFileAssignmentService {

    private final ArticleRepository repository;
    private final ArticlePublisher publisher;

    @Override
    public List<Article> get() {
        return repository.findAll();
    }

    @Override
    public Article create(Article article) {
        Article saved = repository.save(article);
        publisher.publish("Article created. Id: " + article.getId() +
                                  " Title: " + article.getTitle() +
                                  " Author: " + article.getAuthor() +
                                  " Abstract: " + article.getAbstractText());
        return saved;
    }

    @Override
    public Optional<Article> getById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Article update(Long id, Article article) {
        Article entity = getById(id).orElse(new Article());
        entity.setId(id);
        entity.setTitle(article.getTitle());
        entity.setAbstractText(article.getAbstractText());
        entity.setCreationDate(article.getCreationDate());
        entity.setAuthor(article.getAuthor());
        entity.setAbstractText(article.getAbstractText());
        return repository.save(entity);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public void assignFile(ArticleFile articleFile) {
        Optional<Article> oEntity = getById(articleFile.getArticleId());
        if (oEntity.isPresent()) {
            Article article = oEntity.get();
            article.setFilename(article.getFilename());
            repository.save(article);
        }
        else {
            log.error("File assignment failed. Article id {} not found", articleFile.getArticleId());
        }
    }

    @Override
    public void deleteFile(Long id) {
        Optional<Article> oEntity = getById(id);
        if (oEntity.isPresent()) {
            Article article = oEntity.get();
            article.setFilename(null);
            repository.save(article);
        }
        else {
            log.error("Article id {} not found", id);
        }
    }
}
