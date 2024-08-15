package org.aszjch.demoapp.domain.port;

import org.aszjch.demoapp.domain.Article;

import java.util.List;
import java.util.Optional;

public interface ArticleRepository {

  Article save(final Article article);

  Optional<Article> findById(final Long id);

  List<Article> findAll();

  void deleteById(final Long id);

}
