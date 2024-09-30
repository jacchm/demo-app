package org.aszjch.demoapp.domain.article.port;

import org.aszjch.demoapp.TestClockBean;
import org.aszjch.demoapp.TestcontainersConfiguration;
import org.aszjch.demoapp.domain.article.Article;
import org.aszjch.demoapp.infrastructure.repository.TruncatingRepository;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import java.time.Clock;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@Import({TestcontainersConfiguration.class, TestClockBean.class})
class PostgresArticleRepositoryTest {

    @Autowired
    private ArticleRepository postgresArticleRepository;
    @Autowired
    private TruncatingRepository truncatingRepository;
    @Autowired
    private Clock clock;

    @BeforeEach
    void cleanTable() {
        truncatingRepository.truncateTable();
    }

    @Test
    void saves_article() {
        final Article article = createArticle("Test title", "Test author", "Test abstract text");

        final Article saved = postgresArticleRepository.save(article);

        assertNotNull(saved);
        assertAll(
                () -> assertNotNull(saved.getId()),
                () -> assertEquals(article.getTitle(), saved.getTitle()),
                () -> assertEquals(article.getAuthor(), saved.getAuthor()),
                () -> assertEquals(article.getAbstractText(), saved.getAbstractText()),
                () -> assertEquals(OffsetDateTime.now(clock), saved.getCreationDate()),
                () -> assertNull(saved.getFilename()));
    }

    private @NotNull Article createArticle(final String title, final String author,
                                           final String abstractText) {
        final Article article = new Article();
        article.setTitle(title);
        article.setAuthor(author);
        article.setAbstractText(abstractText);
        article.setCreationDate(OffsetDateTime.now(clock));
        return article;
    }

    @Test
    void finds_article_by_id() {
        final Article article = createArticle("Test title", "Test author", "Test abstract text");
        final Article saved = postgresArticleRepository.save(article);

        final Optional<Article> optionalArticle = postgresArticleRepository.findById(saved.getId());

        assertTrue(optionalArticle.isPresent());
        final Article readArticle = optionalArticle.get();
        assertAll(
                () -> assertEquals(saved.getId(), readArticle.getId()),
                () -> assertEquals(saved.getTitle(), readArticle.getTitle()),
                () -> assertEquals(saved.getAuthor(), readArticle.getAuthor()),
                () -> assertEquals(saved.getAbstractText(), readArticle.getAbstractText()),
                () -> assertEquals(saved.getCreationDate()
                                           .atZoneSameInstant(ZoneId.systemDefault())
                                           .truncatedTo(ChronoUnit.MILLIS), readArticle.getCreationDate()
                                           .atZoneSameInstant(ZoneId.systemDefault())
                                           .truncatedTo(ChronoUnit.MILLIS)),
                () -> assertEquals(saved.getFilename(), readArticle.getFilename()));

    }

    @Test
    void finds_all_articles() {
        final Article article = createArticle("Test title", "Test author", "Test abstract text");
        postgresArticleRepository.save(article);
        final Article article2 = createArticle("Test title2", "Test author2",
                                               "Test abstract text2");
        postgresArticleRepository.save(article2);

        final List<Article> articles = postgresArticleRepository.findAll();

        assertEquals(2, articles.size());
    }

    @Test
    void deletes_article_by_id() {
        final Article article = createArticle("Test title", "Test author", "Test abstract text");
        postgresArticleRepository.save(article);
        final Article article2 = createArticle("Test title2", "Test author2",
                                               "Test abstract text2");
        final Article saved = postgresArticleRepository.save(article2);

        postgresArticleRepository.deleteById(saved.getId());

        final List<Article> articles = postgresArticleRepository.findAll();
        assertEquals(1, articles.size());
        final Article first = articles.getFirst();
        assertAll(
                () -> assertEquals(article.getTitle(), first.getTitle()),
                () -> assertEquals(article.getAuthor(), first.getAuthor()),
                () -> assertEquals(article.getAbstractText(), first.getAbstractText()),
                () -> assertEquals(article.getCreationDate()
                                           .atZoneSameInstant(ZoneId.systemDefault())
                                           .truncatedTo(ChronoUnit.MILLIS), first.getCreationDate()
                                           .atZoneSameInstant(ZoneId.systemDefault())
                                           .truncatedTo(ChronoUnit.MILLIS)),
                () -> assertEquals(article.getFilename(), first.getFilename()));
    }

}