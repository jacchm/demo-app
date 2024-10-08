package org.aszjch.demoapp.infrastructure.storage;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aszjch.demoapp.infrastructure.config.TempFilesProperties;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

@Slf4j
@Component
@Named("MultipartFileToFileConverter")
@RequiredArgsConstructor
public class MultipartFileToFileConverter {

    private final TempFilesProperties tempFilesProperties;

    @PostConstruct
    public void init() {
        final Path path = Path.of(tempFilesProperties.getDir());
        final File pathAsFile = path.toFile();
        if (!pathAsFile.exists() || !pathAsFile.isDirectory()) {
            final boolean result = pathAsFile.mkdir();
            log.debug("Temp path creation result: {}", result);
        }
    }

    @Named("convert")
    public File convertMultipartFile(final MultipartFile multipartFile) throws IOException {
        final File file = Path.of(tempFilesProperties.getDir(), multipartFile.getOriginalFilename())
                .toFile();
        final boolean result = file.createNewFile();
        if (result) {
            multipartFile.transferTo(file);
        }
        else {
            log.error("Failed to create temp file");
        }
        return file;
    }
}
