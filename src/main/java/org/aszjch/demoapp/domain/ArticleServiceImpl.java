package org.aszjch.demoapp.domain;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aszjch.demoapp.domain.port.ArticlePublisher;
import org.aszjch.demoapp.domain.port.ArticleRepository;

import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
public class ArticleServiceImpl implements ArticleService {

  private final ArticleRepository repository;
  private final ArticlePublisher publisher;

  @Override
  public List<Article> get() {
    return repository.findAll();
  }

  @Override
  public Article create(Article article) {
    Article saved = repository.save(article);
    publisher.publish("Article created: " + saved.getId()); // todo: probably to be deleted, just an example
    return saved;
  }

  @Override
  public Optional<Article> getById(Long id) {
    return repository.findById(id);
  }

}
