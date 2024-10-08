package org.aszjch.demoapp.domain.articlefile.port;

import org.aszjch.demoapp.domain.articlefile.ArticleFile;

import java.util.Optional;

public interface ArticleFileAssignmentService {

    void assignFile(ArticleFile articleFile);

    Optional<String> unassignFile(Long id);
}
