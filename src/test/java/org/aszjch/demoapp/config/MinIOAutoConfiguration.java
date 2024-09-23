package org.aszjch.demoapp.config;

import io.minio.MinioClient;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@AutoConfiguration
@EnableConfigurationProperties(MinIOProperties.class)
public class MinIOAutoConfiguration {

    @Bean
    MinioClient minioClient(MinIOProperties minIOProperties) {
        return MinioClient.builder()
                .endpoint(minIOProperties.url())
                .credentials(minIOProperties.username(), minIOProperties.password())
                .build();
    }
    
    @Bean
    @ConditionalOnMissingBean(MinIOConnetionDetails.class)
    MinIOConnetionDetails minIOConnetionDetails(MinIOProperties minIOProperties) {
        return new PropertiesMinIOConnectionDetails(minIOProperties);
    }
}
