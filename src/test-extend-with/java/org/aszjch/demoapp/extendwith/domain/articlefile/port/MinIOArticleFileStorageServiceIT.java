package org.aszjch.demoapp.extendwith.domain.articlefile.port;


import io.minio.MinioClient;
import io.minio.StatObjectArgs;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.aszjch.demoapp.DemoAppApplication;
import org.aszjch.demoapp.domain.articlefile.ArticleFile;
import org.aszjch.demoapp.domain.articlefile.port.ArticleFileStorageService;
import org.aszjch.demoapp.extendwith.tcconfig.KafkaContainerExtension;
import org.aszjch.demoapp.extendwith.tcconfig.MinIOContainerExtension;
import org.aszjch.demoapp.extendwith.tcconfig.PostgresContainerExtension;
import org.aszjch.demoapp.infrastructure.config.MinIOConnetionDetails;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.io.TempDir;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@ExtendWith({MinIOContainerExtension.class, PostgresContainerExtension.class, KafkaContainerExtension.class})
@SpringBootTest(classes = DemoAppApplication.class)
@Slf4j
class MinIOArticleFileStorageServiceIT {

    @Autowired
    private ArticleFileStorageService minIOArticleFileStorageService;
    @Autowired
    private MinioClient minioClient;
    @Autowired
    private MinIOConnetionDetails minIOConnetionDetails;

    @Test
    void publishesArticleFileToMinio(@TempDir final File tempDir) throws Exception {
        final String fileName = "test-file.txt";
        final File file = prepareTestFile(tempDir, fileName);
        final ArticleFile articleFile = prepareArticleFile(file, fileName);

        minIOArticleFileStorageService.store(articleFile);

        assertDoesNotThrow(() -> minioClient.statObject(StatObjectArgs.builder()
                                                                .bucket(minIOConnetionDetails.bucket())
                                                                .object(fileName)
                                                                .build()),
                           "File doesn't exist in MinIO bucket");
    }

    private @NotNull ArticleFile prepareArticleFile(final File file, final String fileName) {
        final ArticleFile articleFile = new ArticleFile();
        articleFile.setFile(file);
        articleFile.setFilename(fileName);
        return articleFile;
    }

    private @NotNull File prepareTestFile(final File tempDir, final String fileName) throws IOException {
        final File file = new File(tempDir + "/" + fileName);
        file.createNewFile();
        final String fileContent = "Hello from Testcontainers";
        FileUtils.write(file, fileContent, StandardCharsets.UTF_8);
        return file;
    }
}