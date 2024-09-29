package org.aszjch.demoapp.infrastructure.repository;

import lombok.RequiredArgsConstructor;
import org.aszjch.demoapp.domain.article.Article;
import org.aszjch.demoapp.domain.article.port.ArticleRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
class PostgresArticleRepositoryImpl implements ArticleRepository {

    private final SpringPostgresArticleRepository repository;
    private final ArticleMapper mapper;

    @Override
    public Article save(Article article) {
        ArticleEntity entity = mapper.toEntity(article);
        ArticleEntity saved = repository.save(entity);

        return mapper.toDomain(saved);
    }

    @Override
    public Optional<Article> findById(Long id) {
        return repository
                .findById(id)
                .map(mapper::toDomain);
    }

    @Override
    public List<Article> findAll() {
        return repository
                .findAll()
                .stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

}
