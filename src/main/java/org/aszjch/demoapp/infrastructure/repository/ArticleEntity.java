package org.aszjch.demoapp.infrastructure.repository;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
class ArticleEntity {
  @Id
  @SequenceGenerator(name = "article_id_seq")
  @GeneratedValue(generator = "article_id_seq", strategy = GenerationType.SEQUENCE)
  private Long id;
}
