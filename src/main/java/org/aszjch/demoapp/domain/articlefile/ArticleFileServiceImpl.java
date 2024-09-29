package org.aszjch.demoapp.domain.articlefile;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aszjch.demoapp.domain.articlefile.port.ArticleFileAssignmentService;
import org.aszjch.demoapp.domain.articlefile.port.ArticleFilePublisher;

@Slf4j
@RequiredArgsConstructor
public class ArticleFileServiceImpl implements ArticleFileService {

    private final ArticleFilePublisher articleFilePublisher;
    private final ArticleFileAssignmentService articleFileAssignmentService;

    @Override
    public void upload(ArticleFile articleFile) {
        articleFilePublisher.publish(articleFile);
        articleFileAssignmentService.assignFile(articleFile);
    }
}
