package org.aszjch.demoapp.domain.article;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.aszjch.demoapp.domain.article.port.ArticlePublisher;
import org.aszjch.demoapp.domain.article.port.ArticleRepository;
import org.aszjch.demoapp.domain.articlefile.ArticleFile;
import org.aszjch.demoapp.domain.articlefile.port.ArticleFileAssignmentService;

import java.io.Serializable;
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
        publisher.publish(
                String.valueOf(new ArticleCreatedDto(saved.getId(), saved.getTitle(), saved.getAuthor(),
                                                     saved.getAbstractText())));
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
            article.setFilename(articleFile.getFilename());
            repository.save(article);
            log.info("File [{}] has been successfully assigned to article [{}].", articleFile.getFilename(),
                     article.getId());
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

    @AllArgsConstructor
    @Getter
    @Setter
    private static class ArticleCreatedDto implements Serializable {

        private Long id;
        private String title;
        private String author;
        private String abstractText;

        @Override
        public String toString() {
            return "Article created{" +
                    "id=" + id +
                    ", title='" + title + '\'' +
                    ", author='" + author + '\'' +
                    ", abstractText='" + abstractText + '\'' +
                    '}';
        }
    }
}
