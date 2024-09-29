package org.aszjch.demoapp.infrastructure.storage;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aszjch.demoapp.infrastructure.config.TempFilesProperties;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

@Slf4j
@Component
@RequiredArgsConstructor
public class TempFilesService {

    private final TempFilesProperties tempFilesProperties;

    @PostConstruct
    public void init() {
        Path path = Path.of(tempFilesProperties.getDir());
        File pathAsFile = path.toFile();
        if (!pathAsFile.exists() || !pathAsFile.isDirectory()) {
            boolean result = pathAsFile.mkdir();
            log.debug("Temp path creation result: {}", result);
        }
    }

    public File convertMultipartFile(MultipartFile multipartFile) throws IOException {
        File file = Path.of(tempFilesProperties.getDir(), multipartFile.getOriginalFilename())
                .toFile();
        boolean result = file.createNewFile();
        if (result) {
            multipartFile.transferTo(file);
        }
        else {
            log.error("Failed to create temp file");
        }
        return file;
    }
}
