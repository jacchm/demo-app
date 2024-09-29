package org.aszjch.demoapp.infrastructure.storage;

import io.minio.ObjectWriteResponse;
import org.springframework.web.multipart.MultipartFile;

public interface ExternalStorageService {

    ObjectWriteResponse upload(MultipartFile file);
}
