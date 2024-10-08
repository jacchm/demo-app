package org.aszjch.demoapp.domain.article.port;

import org.aszjch.demoapp.domain.article.Article;

import java.util.List;
import java.util.Optional;

public interface ArticleRepository {

    Article save(Article article);

    Optional<Article> findById(Long id);

    List<Article> findAll();

    void deleteById(Long id);

}
