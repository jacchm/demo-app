package org.aszjch.demoapp.infrastructure.config;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "temp")
@Getter
public class TempFilesProperties {

    private final String dir = System.getProperty("java.io.tmpdir");

}
