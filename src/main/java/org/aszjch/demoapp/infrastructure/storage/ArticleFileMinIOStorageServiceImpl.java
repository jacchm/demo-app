package org.aszjch.demoapp.infrastructure.storage;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aszjch.demoapp.domain.articlefile.ArticleFile;
import org.aszjch.demoapp.domain.articlefile.port.ArticleFileStorageService;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@Component
@RequiredArgsConstructor
class ArticleFileMinIOStorageServiceImpl implements ArticleFileStorageService {

    private final ExternalStorageClient externalStorageClient;

    @Override
    public void store(final ArticleFile articleFile) {
        log.info("Publishing article file {}", articleFile.getFilename());
        final MultipartFile multipartFile = new CustomMultipartFile(articleFile.getFile());
        externalStorageClient.upload(multipartFile);
    }

    @Override
    public void delete(final ArticleFile articleFile) {
        log.info("Removing article file {}", articleFile.getFilename());
        externalStorageClient.delete(articleFile);
    }
}
