package org.aszjch.demoapp.domain.articlefile;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aszjch.demoapp.domain.articlefile.port.ArticleFileAssignmentService;
import org.aszjch.demoapp.domain.articlefile.port.ArticleFileStorageService;

@Slf4j
@RequiredArgsConstructor
class ArticleFileServiceImpl implements ArticleFileService {

    private final ArticleFileStorageService articleFileStorageService;
    private final ArticleFileAssignmentService articleFileAssignmentService;

    @Override
    public void upload(final ArticleFile articleFile) {
        articleFileStorageService.store(articleFile);
        articleFileAssignmentService.assignFile(articleFile);
    }

    @Override
    public void delete(final ArticleFile articleFile) {
        articleFileStorageService.delete(articleFile);
    }
}
