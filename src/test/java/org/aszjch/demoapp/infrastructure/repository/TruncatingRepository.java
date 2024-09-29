package org.aszjch.demoapp.infrastructure.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface TruncatingRepository extends JpaRepository<ArticleEntity, Long> {

    @Modifying
    @Transactional
    @Query(value = "TRUNCATE TABLE article_entity;", nativeQuery = true)
    void truncateTable();
}