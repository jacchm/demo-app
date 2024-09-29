package org.aszjch.demoapp.infrastructure.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "minio")
record MinIOProperties(String url, String username, String password, String bucket) {

}
