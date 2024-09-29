package org.aszjch.demoapp.infrastructure.storage;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aszjch.demoapp.domain.articlefile.ArticleFile;
import org.aszjch.demoapp.domain.articlefile.port.ArticleFilePublisher;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

@Slf4j
@Component
@RequiredArgsConstructor
class ArticleFileMinIOPublisherImpl implements ArticleFilePublisher {

    private final ExternalStorageService externalStorageService;

    @Override
    public void publish(ArticleFile articleFile) {
        log.info("Publishing article file {}", articleFile.getFilename());
        MultipartFile multipartFile = new CustomMultipartFile(articleFile.getFile());
        externalStorageService.upload(multipartFile);
    }

    public static class CustomMultipartFile implements MultipartFile {

        private final File file;

        public CustomMultipartFile(File file) {
            this.file = file;
        }

        @Override
        public String getName() {
            return file.getName();
        }

        @Override
        public String getOriginalFilename() {
            return file.getName();
        }

        @Override
        public String getContentType() {
            try {
                return Files.probeContentType(file.toPath());
            } catch (IOException e) {
                log.error("File publishing failed. Unable to probe content type {}", getName(), e);
                return MediaType.TEXT_PLAIN.getType();
            }
        }

        @Override
        public boolean isEmpty() {
            return file.length() == 0;
        }

        @Override
        public long getSize() {
            return file.length();
        }

        @Override
        public byte[] getBytes() throws IOException {
            return Files.readAllBytes(file.toPath());
        }

        @Override
        public InputStream getInputStream() throws IOException {
            return new FileInputStream(file);
        }

        @Override
        public void transferTo(File dest) throws IOException, IllegalStateException {
            throw new UnsupportedOperationException();
        }
    }
}
