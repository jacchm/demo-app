package org.aszjch.demoapp.domain;

import java.util.List;
import java.util.Optional;

public interface ArticleService {

    List<Article> get();

    Article create(Article article);

    Optional<Article> getById(Long id);

    Article update(Article article);

    void delete(Long id);

}
