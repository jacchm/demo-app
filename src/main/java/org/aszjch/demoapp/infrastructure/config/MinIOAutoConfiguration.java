package org.aszjch.demoapp.infrastructure.config;

import io.minio.MinioClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@AutoConfiguration
@EnableConfigurationProperties(MinIOProperties.class)
@Slf4j
public class MinIOAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean(MinIOConnetionDetails.class)
    MinIOConnetionDetails minIOConnetionDetails(MinIOProperties minIOProperties) {
        return new PropertiesMinIOConnectionDetails(minIOProperties);
    }

    @Bean
    MinioClient minioClient(MinIOConnetionDetails minIOConnectionDetails) {
        log.debug("MinIO connection details: \nurl={}\nusername={}\npassword={}", minIOConnectionDetails.url(), minIOConnectionDetails.username(), minIOConnectionDetails.password());
        return MinioClient.builder()
                .endpoint(minIOConnectionDetails.url())
                .credentials(minIOConnectionDetails.username(), minIOConnectionDetails.password())
                .build();
    }
}
