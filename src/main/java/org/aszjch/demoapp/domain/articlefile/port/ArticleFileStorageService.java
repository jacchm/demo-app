package org.aszjch.demoapp.domain.articlefile.port;

import org.aszjch.demoapp.domain.articlefile.ArticleFile;

public interface ArticleFileStorageService {

    void store(ArticleFile articleFile);
}
