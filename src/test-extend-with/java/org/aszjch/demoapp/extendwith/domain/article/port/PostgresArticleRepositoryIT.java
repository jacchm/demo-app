package org.aszjch.demoapp.extendwith.domain.article.port;

import org.aszjch.demoapp.DemoAppApplication;
import org.aszjch.demoapp.TestClockBean;
import org.aszjch.demoapp.domain.article.Article;
import org.aszjch.demoapp.domain.article.port.ArticleRepository;
import org.aszjch.demoapp.extendwith.tcconfig.KafkaContainerExtension;
import org.aszjch.demoapp.extendwith.tcconfig.MinIOContainerExtension;
import org.aszjch.demoapp.extendwith.tcconfig.PostgresContainerExtension;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import java.time.Clock;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(classes = DemoAppApplication.class)
@Import({TestClockBean.class})
@ExtendWith({MinIOContainerExtension.class, PostgresContainerExtension.class, KafkaContainerExtension.class})
class PostgresArticleRepositoryIT {

    @Autowired
    private ArticleRepository postgresArticleRepository;
    @Autowired
    private Clock clock;

    @Test
    void savesArticle() {
        final Article article = createArticle();

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

    private @NotNull Article createArticle() {
        final Article article = new Article();
        article.setTitle("Test title");
        article.setAuthor("Test author");
        article.setAbstractText("Test abstract text");
        article.setCreationDate(OffsetDateTime.now(clock));
        return article;
    }

    @Test
    void findsArticleById() {
        final Optional<Article> optionalArticle = postgresArticleRepository.findById(1L);

        assertTrue(optionalArticle.isPresent());
        final Article readArticle = optionalArticle.get();
        assertAll(
                () -> assertEquals(1, readArticle.getId()),
                () -> assertEquals("Test title", readArticle.getTitle()),
                () -> assertEquals("Test author", readArticle.getAuthor()),
                () -> assertEquals("Test abstract text", readArticle.getAbstractText()),
                () -> assertEquals(OffsetDateTime.parse("2024-10-03T10:00:00+00:00")
                                           .atZoneSameInstant(ZoneId.systemDefault())
                                           .truncatedTo(ChronoUnit.MILLIS), readArticle.getCreationDate()
                                           .atZoneSameInstant(ZoneId.systemDefault())
                                           .truncatedTo(ChronoUnit.MILLIS)),
                () -> assertNull(readArticle.getFilename()));

    }

    @Test
    void findsAllArticles() {
        final List<Article> articles = postgresArticleRepository.findAll();

        assertThat(articles)
                .hasSizeGreaterThanOrEqualTo(4);
    }

    @Test
    void deletesArticleById() {
        postgresArticleRepository.deleteById(4L);

        final List<Article> articles = postgresArticleRepository.findAll();

        assertThat(articles)
                .extracting(Article::getId)
                .isNotEmpty()
                .doesNotContain(4L);
    }

}