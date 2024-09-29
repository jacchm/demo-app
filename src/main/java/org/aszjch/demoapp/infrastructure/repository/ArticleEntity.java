package org.aszjch.demoapp.infrastructure.repository;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import lombok.Data;

import java.time.OffsetDateTime;

@Data
@Entity
class ArticleEntity {

    @Id
    @SequenceGenerator(name = "article_id_seq")
    @GeneratedValue(generator = "article_id_seq", strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String author;
    @Column(nullable = false)
    private OffsetDateTime creationDate;
    @Column
    private String abstractText;
    @Column
    private String filename;
}
