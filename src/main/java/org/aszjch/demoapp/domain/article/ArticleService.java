package org.aszjch.demoapp.domain.article;

import java.util.List;
import java.util.Optional;

public interface ArticleService {

    List<Article> get();

    Article create(Article article);

    Optional<Article> getById(Long id);

    Article update(Long id, Article article);

    void delete(Long id);

}
