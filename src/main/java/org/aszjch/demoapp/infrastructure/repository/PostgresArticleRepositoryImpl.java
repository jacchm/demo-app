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
    private final ArticleEntityMapper mapper;

    @Override
    public Article save(final Article article) {
        final ArticleEntity entity = mapper.toEntity(article);
        final ArticleEntity saved = repository.save(entity);

        return mapper.toDomain(saved);
    }

    @Override
    public Optional<Article> findById(final Long id) {
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
    public void deleteById(final Long id) {
        repository.deleteById(id);
    }

}
