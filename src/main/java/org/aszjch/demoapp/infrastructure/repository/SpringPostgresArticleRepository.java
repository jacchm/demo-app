package org.aszjch.demoapp.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface SpringPostgresArticleRepository extends JpaRepository<ArticleEntity, Long> {
}
