package org.aszjch.demoapp.domain.article.port;

import org.aszjch.demoapp.domain.article.ArticleCreatedDto;

public interface ArticlePublisher {

    void publish(ArticleCreatedDto articleCreatedDto);

}
