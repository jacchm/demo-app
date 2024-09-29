package org.aszjch.demoapp.domain.articlefile.port;


import io.minio.ListObjectsArgs;
import io.minio.MinioClient;
import io.minio.Result;
import io.minio.messages.Item;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.aszjch.demoapp.TestcontainersConfiguration;
import org.aszjch.demoapp.domain.articlefile.ArticleFile;
import org.aszjch.demoapp.infrastructure.config.MinIOConnetionDetails;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Import(TestcontainersConfiguration.class)
@SpringBootTest
@Slf4j
class MinIOArticleFilePublisherIT {

    @Autowired
    private ArticleFilePublisher minIOArticleFilePublisher;
    @Autowired
    private MinioClient minioClient;
    @Autowired
    private MinIOConnetionDetails minIOConnetionDetails;

    @Test
    void publishes_article_file_to_minio(@TempDir File tempDir) throws Exception {
        String fileName = "test-file.txt";
        File file = new File(tempDir + "/" + fileName);
        file.createNewFile();
        String fileContent = "Hello from Testcontainers";
        FileUtils.write(file, fileContent, StandardCharsets.UTF_8);
        ArticleFile articleFile = new ArticleFile();
        articleFile.setFile(file);
        articleFile.setFilename(fileName);

        minIOArticleFilePublisher.publish(articleFile);


        Iterable<Result<Item>> results = minioClient.listObjects(ListObjectsArgs.builder()
                                                                         .bucket(minIOConnetionDetails.bucket())
                                                                         .build());
        ArrayList<Result<Item>> resultList = new ArrayList<>();
        results.forEach(resultList::add);
        assertEquals(1, resultList.size());
        Item firstResult = resultList.getFirst()
                .get();
        assertEquals(fileName, firstResult.objectName());
        log.info("Filename read from minio: {}", firstResult.objectName());

    }
}