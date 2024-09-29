package org.aszjch.demoapp.domain.articlefile.port;

import org.aszjch.demoapp.domain.articlefile.ArticleFile;

public interface ArticleFilePublisher {

    void publish(ArticleFile articleFile);
}
