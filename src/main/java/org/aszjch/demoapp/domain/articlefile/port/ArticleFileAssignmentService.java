package org.aszjch.demoapp.domain.articlefile.port;

import org.aszjch.demoapp.domain.articlefile.ArticleFile;

public interface ArticleFileAssignmentService {

    void assignFile(ArticleFile articleFile);

    void unassignFile(Long id);
}
