package org.aszjch.demoapp.infrastructure.storage;

import io.minio.ObjectWriteResponse;
import org.aszjch.demoapp.domain.articlefile.ArticleFileMetadata;
import org.springframework.web.multipart.MultipartFile;

public interface ExternalStorageClient {

    ObjectWriteResponse upload(MultipartFile file);

    void delete(ArticleFileMetadata articleFileMetadata);
}
