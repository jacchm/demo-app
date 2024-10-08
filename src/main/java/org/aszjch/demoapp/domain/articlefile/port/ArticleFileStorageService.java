package org.aszjch.demoapp.domain.articlefile.port;

import org.aszjch.demoapp.domain.articlefile.ArticleFile;
import org.aszjch.demoapp.domain.articlefile.ArticleFileMetadata;

public interface ArticleFileStorageService {

    void store(ArticleFile articleFile);

    void delete(ArticleFileMetadata articleFileMetadata);
}
